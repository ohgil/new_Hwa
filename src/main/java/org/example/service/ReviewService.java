package org.example.service;

import org.example.Container;
import org.example.dto.Review;
import org.example.repository.ReviewRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewService() {
        reviewRepository = Container.reviewRepository;
    }

    public int write(int product_id, String review, int grade) {
        return reviewRepository.write(product_id, review, grade);
    }

    public Review getReviewById(int id) {
        return ReviewRepository.getReviewById(id);
    }

    public boolean reviewExists(int id) {
        return reviewRepository.reviewExists(id);
    }

    public void delete(int id) {
        reviewRepository.delete(id);
    }

    public void update(int product_id, String review, int grade) {
        reviewRepository.update(product_id, review, grade);
    }




    public boolean reviewNameExists(String reviewName) {
        return reviewRepository.reviewNameExists(reviewName);
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
