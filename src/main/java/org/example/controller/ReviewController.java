package org.example.controller;


import org.example.Container;
import org.example.dto.Article;
import org.example.dto.Review;
import org.example.service.ArticleService;
import org.example.service.ReviewService;

import java.util.List;

import static org.example.Container.rq;

public class ReviewController {

    private ReviewService reviewService;

    public ReviewController() {
        reviewService = Container.reviewService;
    }

    public void write() {
        if(Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }

        System.out.println("== 리뷰 ==");
        System.out.printf("제목 : ");
        String text = Container.scanner.nextLine();
        System.out.printf("내용 : ");
        double grade = Double.parseDouble(Container.scanner.nextLine());

        int member_id = Container.session.loginedMemberId;
        int id = reviewService.write(member_id, text, grade);

        System.out.printf("%d번 리뷰가 등록되었습니다.\n", id);
    }
    public void showList() {
        System.out.println("== 리뷰 목록 ==");
        int page = rq.getIntParam("page", 1);
        String searchKeyword = rq.getParam("searchKeyword", "");
        int pageItemCount = 10;

        // 임시
        pageItemCount = 5;

        List<Review> reviews = reviewService.getForPrintArticleById(page, pageItemCount, searchKeyword);

        if (reviews.isEmpty()) {
            System.out.println("리뷰가 존재하지 않습니다.");
            return;
        }

        System.out.println("번호 / 작성자 / 리뷰 / 평점");

        for (Review review : reviews) {
            System.out.printf("%d / %s / %s / %.1f\n", review.id, review.extra__writerName, review.text, review.grade);
        }
    }

    public void showDetail() {
        int id = rq.getIntParam("id", 0);

        if (id == 0) {
            System.out.println("id를 올바르게 입력해주세요.");
            return;
        }
    }

    public void modify() {
        if(Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }

        int id = rq.getIntParam("id", 0);

        if (id == 0) {
            System.out.println("id를 올바르게 입력해주세요.");
            return;
        }

        Review review = reviewService.getReviewById(id);

        boolean reviewExists = reviewService.reviewExists(id);

        if (reviewExists == false) {
            System.out.printf("%d번 리뷰는 존재하지 않습니다.\n", id);
            return;
        }

        if(review.member_id != Container.session.loginedMemberId) {
            System.out.println("권한이 없습니다");
            return;
        }

        System.out.printf("새 리뷰 : ");
        String text = Container.scanner.nextLine();
        System.out.printf("새 평점 : ");
        double grade = Container.scanner.nextDouble();

        reviewService.update(id, text, grade);

        System.out.printf("%d번 리뷰가 수정되었습니다.\n", id);
    }

    public void delete() {
        if(Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }

        int id = rq.getIntParam("id", 0);

        if (id == 0) {
            System.out.println("id를 올바르게 입력해주세요.");
            return;
        }

        System.out.printf("== %d번 리뷰 삭제 ==\n", id);

        Review review = reviewService.getReviewById(id);

        boolean reviewExists = reviewService.reviewExists(id);

        if (reviewExists == false) {
            System.out.printf("%d번 리뷰는 존재하지 않습니다.\n", id);
            return;
        }

        if(review.member_id != Container.session.loginedMemberId) {
            System.out.println("권한이 없습니다");
            return;
        }

        reviewService.delete(id);

        System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
    }

}
