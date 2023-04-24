package org.example.repository;

import org.example.Container;
import org.example.dto.Member;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.Map;

public class MemberRepository {

  public boolean isLoginIdDup(String member_email) {
    SecSql sql = new SecSql();

    sql.append("SELECT COUNT(*) > 0");
    sql.append("FROM `member`");
    sql.append("WHERE member_email = ?", member_email);

    return DBUtil.selectRowBooleanValue(Container.conn, sql); // member_id 로 가입된 정보가 있으면 true 값 반환 없으면 false
  }

  public int join(String member_email, String member_pwd, String member_name, String member_birth, String member_gender) {
    SecSql sql = new SecSql();
    sql.append("INSERT INTO member");
    sql.append("SET member_email = ?", member_email);
    sql.append(", member_pwd = ?", member_pwd);
    sql.append(", member_name = ?", member_name);
    sql.append(", member_birth = ?", member_birth);
    sql.append(", member_gender = ?", member_gender);

    /*
    INSERT INTO `member`
    set member_grade = 1,
    member_email = 'asdf@asdf.com',
    member_pwd = 123,
    member_name = '인성',
    member_birth = '1993-11-03',
    member_gender = 2,
    member_status = 1;
);
     */

    int id = DBUtil.insert(Container.conn, sql);

    return id;
  }

  public Member getMemberByLoginId(String member_email) {
    SecSql sql = new SecSql();

    sql.append("SELECT *");
    sql.append("FROM `member`");
    sql.append("WHERE member_email = ?", member_email);

    Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);

    if(memberMap.isEmpty()) {
      return null;
    }

    return new Member(memberMap);
  }
}
