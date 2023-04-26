package org.example.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SecSql { // JDBC를 이용하여 데이터베이스 작업을 수행할 때 SQL 쿼리와 파라미터 값을 안전하게 처리할 수 있도록 도와주는 유틸리티 클래스인 SecSql을 정의
  private StringBuilder sqlBuilder;
  private List<Object> datas;

  @Override
  public String toString() {
    return "sql=" + getFormat() + ", data=" + datas;
  }

  public SecSql() {
    sqlBuilder = new StringBuilder();
    datas = new ArrayList<>();
  }

  public boolean isInsert() {
    return getFormat().startsWith("INSERT");
  }

  public SecSql append(Object... args) {
    if (args.length > 0) {
      String sqlBit = (String) args[0];
      sqlBuilder.append(sqlBit + " ");
    }

    for (int i = 1; i < args.length; i++) {
      datas.add(args[i]);
    }

    return this;
  }

  public PreparedStatement getPreparedStatement(Connection dbConn) throws SQLException {
    PreparedStatement stmt = null;

    if (isInsert()) {
      stmt = dbConn.prepareStatement(getFormat(), Statement.RETURN_GENERATED_KEYS);
    } else {
      stmt = dbConn.prepareStatement(getFormat());
    }

    for (int i = 0; i < datas.size(); i++) {
      Object data = datas.get(i);
      int parameterIndex = i + 1;

      if (data instanceof Integer) {
        stmt.setInt(parameterIndex, (int) data);
      } else if (data instanceof String) {
        stmt.setString(parameterIndex, (String) data);
      } else if (data instanceof  Double) {
        stmt.setDouble(parameterIndex, (double) data);
      }
    }

    return stmt;
  }

  public String getFormat() {
    return sqlBuilder.toString();
  }

  public static SecSql from(String sql) {
    return new SecSql().append(sql);
  }
}
