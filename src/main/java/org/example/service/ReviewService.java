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

    public int write(int member_id, String review, double grade) {
        return reviewRepository.write(member_id, review, grade);
    }

    public boolean reviewExists(int id) {
        return reviewRepository.reviewExists(id);
    }

    public void delete(int id) {
        reviewRepository.delete(id);
    }

    public void update(int member_id, String review, double grade) {
        reviewRepository.update(member_id, review, grade);
    }

    public Review getReviewById(int id) {
        return reviewRepository.getReviewById(id);
    }


    public List<Review> getForPrintArticleById(int page, int pageItemCount, String searchKeyword) {
        int limitFrom = (page - 1) * pageItemCount;
        int limitTake = pageItemCount;

        Map<String, Object> args = new HashMap<>();
        args.put("limitFrom", limitFrom);
        args.put("limitTake", limitTake);
        return reviewRepository.getReviews(args, searchKeyword);
    }
}
