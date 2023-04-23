package org.example.dto;

import lombok.Data;

import java.util.Map;
// care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation
@Data
public class Product {
  public int id;
  public String care;
  public String skin_type;
  public String product_name;
  public String product_brand;
  public String product_capacity;
  public String product_price;
  public String product_explanation;

  public Product(Map<String, Object> productMap) {
    this.id = (int) productMap.get("id");
    this.care = (String) productMap.get("care");
    this.skin_type = (String) productMap.get("skin_type");
    this.product_name = (String) productMap.get("product_name");
    this.product_brand = (String) productMap.get("product_brand");
    this.product_capacity = (String) productMap.get("product_capacity");
    this.product_price = (String) productMap.get("product_price");
    this.product_explanation = (String) productMap.get("product_explanation");
  }
}
