package org.example.util;

import org.example.exception.SQLErrorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil { // 현재 db 연결 정보랑 인텔리제이에서 db 에 날릴 쿼리문을 넘겨주면 실제로 db에 쿼리문을 전송해서 전송한 결과에대한 정보를 가져온다. (상호작용)
  public static Map<String, Object> selectRow(Connection dbConn, SecSql sql) { // selectRow 는 select 의 결과가 1줄만 있을 때 사용 -> map 받아옴
    List<Map<String, Object>> rows = selectRows(dbConn, sql); // object 는 최상위 자료형이니까 실제로 사용하려면 수동형변환 필요

    if (rows.size() == 0) {
      return new HashMap<>();
    }

    return rows.get(0);
  }

  public static List<Map<String, Object>> selectRows(Connection dbConn, SecSql sql) throws SQLErrorException { // selectRows 는 select 의 결과가 여러줄만 있을 때 사용 -> list map 받아옴
    List<Map<String, Object>> rows = new ArrayList<>(); // object 는 최상위 자료형이니까 실제로 사용하려면 수동형변환 필요

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = sql.getPreparedStatement(dbConn);
      rs = stmt.executeQuery();
      ResultSetMetaData metaData = rs.getMetaData();
      int columnSize = metaData.getColumnCount();

      while (rs.next()) {
        Map<String, Object> row = new HashMap<>();

        for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
          String columnName = metaData.getColumnName(columnIndex + 1);
          Object value = rs.getObject(columnName);

          if (value instanceof Long) {
            int numValue = (int) (long) value;
            row.put(columnName, numValue);
          } else if (value instanceof Timestamp) {
            String dateValue = value.toString();
            dateValue = dateValue.substring(0, dateValue.length() - 2);
            row.put(columnName, dateValue);
          } else {
            row.put(columnName, value);
          }
        }

        rows.add(row);
      }
    } catch (SQLException e) {
      throw new SQLErrorException("SQL 예외, SQL : " + sql, e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          throw new SQLErrorException("SQL 예외, rs 닫기, SQL : " + sql, e);
        }
      }

      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          throw new SQLErrorException("SQL 예외, stmt 닫기, SQL : " + sql, e);
        }
      }
    }

    return rows;
  }

  public static int selectRowIntValue(Connection dbConn, SecSql sql) {
    Map<String, Object> row = selectRow(dbConn, sql);

    for (String key : row.keySet()) {
      return (int) row.get(key);
    }

    return -1;
  }

  public static String selectRowStringValue(Connection dbConn, SecSql sql) {
    Map<String, Object> row = selectRow(dbConn, sql);

    for (String key : row.keySet()) {
      return (String) row.get(key);
    }

    return "";
  }

  public static boolean selectRowBooleanValue(Connection dbConn, SecSql sql) { // 특정조건이 만족하는 것들이 1개 이상이 있는지 확인할 때 사용
    Map<String, Object> row = selectRow(dbConn, sql);

    for (String key : row.keySet()) {
      return ((int) row.get(key)) == 1;
    }

    return false;
  }

  public static int insert(Connection dbConn, SecSql sql) {
    int id = -1;

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = sql.getPreparedStatement(dbConn);
      stmt.executeUpdate();
      rs = stmt.getGeneratedKeys();

      if (rs.next()) {
        id = rs.getInt(1);
      }

    } catch (SQLException e) {
      throw new SQLErrorException("SQL 예외, SQL : " + sql, e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          throw new SQLErrorException("SQL 예외, rs 닫기, SQL : " + sql, e);
        }
      }

      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          throw new SQLErrorException("SQL 예외, stmt 닫기, SQL : " + sql, e);
        }
      }

    }

    return id;
  }

  public static int update(Connection dbConn, SecSql sql) {
    int affectedRows = 0;

    PreparedStatement stmt = null;

    try {
      stmt = sql.getPreparedStatement(dbConn);
      affectedRows = stmt.executeUpdate();
    } catch (SQLException e) {
      throw new SQLErrorException("SQL 예외, SQL : " + sql, e);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          throw new SQLErrorException("SQL 예외, stmt 닫기, SQL : " + sql, e);
        }
      }
    }

    return affectedRows;
  }

  public static int delete(Connection dbConn, SecSql sql) {
    return update(dbConn, sql);
  }
}