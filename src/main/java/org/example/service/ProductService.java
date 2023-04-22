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

  public int write(int memberId, String title, String body) {
    return productRepository.write(memberId, title, body);
  }

  public boolean productExists(int id) {
    return productRepository.productExists(id);
  }

  public void delete(int id) {
    productRepository.delete(id);
  }

  public void update(int id, String title, String body) {
    productRepository.update(id, title, body);
  }

  public Product getProductById(int id) {
    return productRepository.getProductById(id);
  }

  public void increaseHit(int id) {
    productRepository.increaseHit(id);
  }

  public List<Product> getForPrintProductById(int page, int pageItemCount, String searchKeyword) {
    int limitFrom = (page - 1) * pageItemCount;
    int limitTake = pageItemCount;

    Map<String, Object> args = new HashMap<>();
    args.put("limitFrom", limitFrom);
    args.put("limitTake", limitTake);
    return productRepository.getProducts(args, searchKeyword);
  }
}
