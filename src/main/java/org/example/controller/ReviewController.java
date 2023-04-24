package org.example.controller;


import org.example.Container;
import org.example.dto.Product;
import org.example.dto.Review;
import org.example.service.ProductService;
import org.example.service.ReviewService;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example.Container.rq;
import static org.example.Container.scanner;

public class ReviewController {

    private ReviewService reviewService;

    public ReviewController() {
        reviewService = Container.reviewService;

    }

    public void write() {
        if (Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }
// care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation
        System.out.println("< 리뷰 등록 >");
        System.out.printf("상품번호 : ");
        int product_id = Container.scanner.nextInt();
        Container.scanner.nextLine();
        System.out.printf("리뷰 내용  : ");
        String review = Container.scanner.nextLine();
        System.out.printf("평점  : ");
        Double grade = Double.valueOf(Container.scanner.nextLine());

        int member_email = Container.session.loginedMemberId;
        int id = reviewService.write(product_id, review, grade);

        System.out.printf("%d번 리뷰가 등록되었습니다.\n", id);
    }

    public void search() {
        System.out.println("< 리뷰 검색 >");
        System.out.print("검색어 : ");
        String word = Container.scanner.nextLine();

        SecSql sql = new SecSql();

        sql.append("SELECT * FROM review");
        sql.append("WHERE review.review");
        sql.append("LIKE 'word%' = ?", word);

//        List<Map<String, Object>> searchedReviewMapList = DBUtil.selectRows(Container.conn, sql);
//        List<Review> searchedReviewList = new ArrayList<>();

//       for (Map<String, Object> searchedReviewMap : searchedReviewMapList) {
//            searchedReviewList.add(new Review(searchedReviewMap));
//        }


        System.out.printf("%s에 대한 검색 결과...\n\n", word);
        System.out.println("상품번호 / 리뷰내용 / 평점");
        System.out.println("-".repeat(60));
        System.out.println("2 / good / 3.5");
//        for (Review review : searchedReviewList) {
//            System.out.printf("%d / %s / %f\n", review.getProduct_id(), review.getReview(), review.getGrade());
//    }

    }


    public void show() {
        Review review = Container.session.getSessionReview();
        System.out.printf("상품 번호 : %d\n", review.getProduct_id());
        System.out.printf("리뷰 내용 : %s\n", review.getReview());
        System.out.printf("별점 : %.1f\n", review.getGrade());
    }

    public void modify() {
        if (Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }


        System.out.printf("수정할 리뷰의 상품 번호를 입력해주세요: ");
        int product_id = scanner.nextInt();
        Container.scanner.nextLine();

        if (!reviewService.reviewExists(product_id)) {
            System.out.printf("%d번 리뷰는 존재하지 않습니다.\n", product_id);
            return;
        }

        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM review");

        Container.session.setSessionReview(new Review(DBUtil.selectRow(Container.conn, sql)));

        System.out.println("수정할 리뷰의 기존정보는 아래와 같습니다.");
        show();

        System.out.println("=".repeat(50));
        System.out.println("수정할 내용을 입력해주세요.");

        System.out.printf("리뷰 내용: ");
        String review = Container.scanner.nextLine().trim();
        if (review.length() == 0) {
            review = Container.session.getSessionReview().getReview();
        }
        System.out.printf("별점: ");
        String grade = Container.scanner.nextLine().trim();
        if (grade.length() == 0) {
            grade = String.valueOf(Container.session.getSessionReview().getGrade());
        }

        reviewService.update(product_id, review, Double.parseDouble(grade));

        System.out.printf("%d번 상품이 수정되었습니다.\n", product_id);
    }

    public void showList() {
        Review review = Container.session.getSessionReview();
        System.out.printf("상품번호 : %s\n", review.getProduct_id());
        System.out.printf("리뷰내용 : %s\n", review.getReview());
        System.out.printf("별점 :  %.1f\n", review.getGrade());
    }

    public void delete() {
        if (Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }

        System.out.printf("삭제할 리뷰 번호를 입력해주세요: ");
        int product_id = scanner.nextInt();
        Container.scanner.nextLine();

        if (!reviewService.reviewExists(product_id)) {
            System.out.printf("%d번 리뷰는 존재하지 않습니다.\n", product_id);
            return;
        }

        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM review");

        Container.session.setSessionReview(new Review(DBUtil.selectRow(Container.conn, sql)));

        System.out.println("삭제할 리뷰의 기존정보는 아래와 같습니다.");
        showList();

        System.out.println("정말 삭제하시겠습니까? (Y/N)");
        System.out.printf(">> ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (!answer.equals("y")) return;

        sql = new SecSql();

        sql.append("DELETE FROM review");
        sql.append("WHERE review.product_id = ?", product_id);

        DBUtil.delete(Container.conn, sql);

        System.out.printf("%d번 리뷰가 삭제되었습니다.\n", product_id);
    }

}