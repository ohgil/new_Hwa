package org.example.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Product {
  public int id;
  public String regDate;
  public String updateDate;
  public int memberId;
  public String title;
  public String body;
  public int hit;
  public String extra__writerName;

  public Product(Map<String, Object> productMap) {
    this.id = (int) productMap.get("id");
    this.regDate = (String) productMap.get("regDate");
    this.updateDate = (String) productMap.get("updateDate");
    this.memberId = (int) productMap.get("memberId");
    this.title = (String) productMap.get("title");
    this.body = (String) productMap.get("body");
    this.hit = (int) productMap.get("hit");

    if(productMap.get("extra__writerName") != null) {
      this.extra__writerName = (String)productMap.get("extra__writerName");
    }
  }
}
