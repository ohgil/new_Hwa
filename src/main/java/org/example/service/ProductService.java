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

<<<<<<< HEAD
  public int write(int memberId, String title, String body) {
    return productRepository.write(memberId, title, body);
  }

  public boolean productExists(int id) {
    return productRepository.productExists(id);
=======
  public int write(String product_name, String product_brand, String product_capacity, String product_price, String product_explanation) {
    return productRepository.write(product_name, product_brand, product_capacity, product_price, product_explanation);
  }

  public boolean articleExists(int id) {
    return productRepository.articleExists(id);
>>>>>>> f90810f (상품등록부분까지)
  }

  public void delete(int id) {
    productRepository.delete(id);
  }

<<<<<<< HEAD
  public void update(int id, String title, String body) {
    productRepository.update(id, title, body);
  }

  public Product getProductById(int id) {
    return productRepository.getProductById(id);
=======
  public void update(String product_name, String product_brand, String product_capacity, String product_price, String product_explanation) {
    productRepository.update(product_name, product_brand, product_capacity, product_price, product_explanation);
  }

  public Product getArticleById(int id) {
    return productRepository.getArticleById(id);
>>>>>>> f90810f (상품등록부분까지)
  }

  public void increaseHit(int id) {
    productRepository.increaseHit(id);
  }

<<<<<<< HEAD
  public List<Product> getForPrintProductById(int page, int pageItemCount, String searchKeyword) {
=======
  public List<Product> getForPrintArticleById(int page, int pageItemCount, String searchKeyword) {
>>>>>>> f90810f (상품등록부분까지)
    int limitFrom = (page - 1) * pageItemCount;
    int limitTake = pageItemCount;

    Map<String, Object> args = new HashMap<>();
    args.put("limitFrom", limitFrom);
    args.put("limitTake", limitTake);
<<<<<<< HEAD
    return productRepository.getProducts(args, searchKeyword);
=======
    return productRepository.getArticles(args, searchKeyword);
>>>>>>> f90810f (상품등록부분까지)
  }
}
