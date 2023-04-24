package org.example.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Member {
  private int id;
  private String member_email;
  private String member_pwd;
  private String member_pwd_confirm;
  private String member_name;
  private String member_birth;
  private String member_gender;


  public Member(Map<String, Object> memberMap) {
    this.id = (int) memberMap.get("id");
    this.member_email = (String) memberMap.get("member_email");
    this.member_pwd = (String) memberMap.get("member_pwd");
    this.member_pwd_confirm = (String) memberMap.get("member_pwd_confirm");
    this.member_name = (String) memberMap.get("member_name");
    this.member_birth = (String) memberMap.get("member_birth");
    this.member_gender = (String) memberMap.get("member_gender");
  }

}
