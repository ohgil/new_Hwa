package org.example.service;

import org.example.Container;
import org.example.dto.Product;
import org.example.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
  private ProductRepository productRepository;
  public ProductService() {
    productRepository = Container.productRepository;
  }

  public int write(int care_id, int type_id, String product_name, String product_brand, String product_capacity, String product_price, String product_explanation) {
    return productRepository.write(care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation);
  }

  public boolean articleExists(int id) {
    return productRepository.articleExists(id);
  }

  public void delete(int id) {
    productRepository.delete(id);
  }

  public void update(int product_id, int care_id, int type_id, String product_name, String product_brand, String product_capacity, String product_price, String product_explanation) {
    productRepository.update(product_id, care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation);
  }

  public Product getProductById(int id) {
    return productRepository.getProductById(id);
  }

  public boolean productExists(int id) {
    return productRepository.productExists(id);
  }

//  public void increaseHit(int id) {
//    productRepository.increaseHit(id);
//  }

//  public List<Product> getForPrintArticleById(int page, int pageItemCount, String searchKeyword) {
//    int limitFrom = (page - 1) * pageItemCount;
//    int limitTake = pageItemCount;
//
//    Map<String, Object> args = new HashMap<>();
//    args.put("limitFrom", limitFrom);
//    args.put("limitTake", limitTake);
//    return productRepository.getArticles(args, searchKeyword);
//  }
}