package org.example.controller;


import org.example.Container;
import org.example.dto.Product;
import org.example.dto.Review;
import org.example.service.ReviewService;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example.Container.rq;
import static org.example.Container.scanner;

public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController() {
        reviewService = Container.reviewService;

    }

    public void write() {
        if (Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }

        System.out.println("< 리뷰 등록 >");
        System.out.printf("상품 번호 : ");
        int product_id = Container.scanner.nextInt();
        Container.scanner.nextLine();
        System.out.printf("리뷰 내용  : ");
        String review = Container.scanner.nextLine();
        System.out.printf("별점  : ");
        int grade = Container.scanner.nextInt();

        int id = reviewService.write(product_id, review, grade);

        System.out.printf("%d번 리뷰가 등록되었습니다.\n", id);
    }

    public void search() {
        System.out.println("< 리뷰 검색 >");

        System.out.println("== 리뷰 리스트 ==");
        SecSql sql = new SecSql();
        sql.append("SELECT review");
        sql.append("FROM review");

        List<Map<String, Object>> reviewList = DBUtil.selectRows(Container.conn, sql);

        for (int i = 0; i < reviewList.size(); i++) {
            System.out.printf("%s", reviewList.get(i).get("review"));
            if (i < reviewList.size() - 1) System.out.printf(", ");
            if (i == reviewList.size() - 1) System.out.printf("\n");
        }

        String reviewName;

        while (true) {
            System.out.printf("검색어를 입력해주세요: ");
            reviewName = Container.scanner.nextLine().trim();

            boolean isInReviewList = false;

            for (Map<String, Object> reviewMap : reviewList) {
                if (reviewName.equals(reviewMap.get("review"))) isInReviewList = true;
            }

            if (isInReviewList) break;

            System.out.println("다시 입력해주세요");
        }

        sql = new SecSql();
        sql.append("SELECT * ");
        sql.append("FROM review");
        sql.append("WHERE review = ?;", reviewName);

        List<Map<String, Object>> searchedReviewMapList = DBUtil.selectRows(Container.conn, sql);

        if(searchedReviewMapList.isEmpty()) {
            System.out.println("검색 결과가 존재하지 않습니다.");
            Container.session.setSessionReview(null);
            return;
        }

        List<Review> searchedReivewList = new ArrayList<>();

        for (Map<String, Object> searchedReviewMap : searchedReviewMapList) {
            searchedReivewList.add(new Review(searchedReviewMap));
        }

        System.out.printf("%s에 대한 검색 결과...\n\n", reviewName);
        System.out.println("상품번호 / 리뷰내용 / 별점");
        System.out.println("-".repeat(60));
        for (Review review : searchedReivewList) {
            System.out.printf("%d / %s / %d \n", review.getProduct_id(), review.getReview(), review.getGrade());
        }

    }
    public void showDetail() {
        Review review = Container.session.getSessionReview();
        if (review == null) return;
        System.out.printf("번호 : %d\n", review.getId());
        // System.out.printf("등록일 : %s\n", article.regDate);
//    System.out.printf("작성자 : %s\n", article.extra__writerName);
//        System.out.printf("작성자 : %d\n", review.getMember_id());
        System.out.printf("상품번호 : %d\n", review.getProduct_id());
        System.out.printf("리뷰내용 : %s\n", review.getReview());
        System.out.printf("별점 : %d\n", review.getGrade());
    }

    public void modify() {
        if (Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }

        System.out.printf("수정할 리뷰 번호를 입력해주세요: ");
        int product_id = Container.scanner.nextInt();
        Container.scanner.nextLine();

        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM review");
        sql.append("WHERE id = ?;", product_id);

        Container.session.setSessionReview(new Review(DBUtil.selectRow(Container.conn, sql)));

        System.out.println("수정할 리뷰 번호의 기존정보는 아래와 같습니다.");
        showDetail();

        System.out.println("=".repeat(50)); // if 문 통해서 수정할 내용을 입력한 정보만 수정이 됨.
        System.out.println("수정할 내용을 입력해주세요.");
        System.out.printf("리뷰 내용: ");
        String review = Container.scanner.nextLine().trim();
        if (review.length() == 0) {
            review = Container.session.getSessionReview().getReview();
        }

        System.out.printf("별점: ");
        int grade = Container.scanner.nextInt();
        if (grade == 0) {
            grade = Container.session.getSessionReview().getGrade();
        }

        reviewService.update(product_id, review, grade);

        System.out.printf("%d번 리뷰가 수정되었습니다.\n", Container.session.getSessionReview().id);
    }

    public void delete() {
        if (Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }

        System.out.printf("삭제할 리뷰 번호를 입력해주세요: ");
        int review_id = scanner.nextInt();
        Container.scanner.nextLine();

        if (!reviewService.reviewExists(review_id)) {
            System.out.printf("%d번 리뷰는 존재하지 않습니다.\n", review_id);
            return;
        }

        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM review");
        sql.append("WHERE review.id = ?;", review_id);

        Container.session.setSessionReview(new Review(DBUtil.selectRow(Container.conn, sql)));

        System.out.println("삭제할 리뷰의 기존정보는 아래와 같습니다.");
        showDetail();

        System.out.println("정말 삭제하시겠습니까? (Y/N)");
        System.out.printf(">> ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (!answer.equals("y")) return;

        sql = new SecSql();

        sql.append("DELETE FROM review");
        sql.append("WHERE id = ?;", review_id);

        DBUtil.delete(Container.conn, sql);

        System.out.printf("%d번 리뷰가 삭제되었습니다.\n", review_id);
    }

}