package org.example.dto;

import lombok.Data;

import java.util.Map;
<<<<<<< HEAD

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
=======
// care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation
@Data
public class Product {
  public int id;
  public int member_id;
  public int care_id;
  public int type_id;
  public String regDate;
  public String product_name;
  public String product_brand;
  public String product_capacity;
  public String product_price;
  public String product_explanation;
  public String extra__writerName;

  public Product(Map<String, Object> articleMap) {
    this.id = (int) articleMap.get("id");
    this.member_id = (int) articleMap.get("memebr_id");
    this.care_id = (int) articleMap.get("care_id");
    this.type_id = (int) articleMap.get("type_id");
    this.regDate = (String) articleMap.get("regDate");
    this.product_name = (String) articleMap.get("product_name");
    this.product_brand = (String) articleMap.get("product_brand");
    this.product_capacity = (String) articleMap.get("product_capacity");
    this.product_price = (String) articleMap.get("product_price");
    this.product_explanation = (String) articleMap.get("product_explanation");

    if(articleMap.get("extra__writerName") != null) {
      this.extra__writerName = (String)articleMap.get("extra__writerName");
>>>>>>> f90810f (상품등록부분까지)
    }
  }
}
