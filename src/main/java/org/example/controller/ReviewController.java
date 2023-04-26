package org.example.controller;


import org.example.Container;
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
        int product_id = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("리뷰 내용  : ");
        String review = scanner.nextLine();
        System.out.printf("별점  : ");
        int grade = scanner.nextInt();
        scanner.nextLine();
        int id = reviewService.write(product_id, review, grade);

        System.out.printf("%d번 리뷰가 등록되었습니다.\n", id);
    }

    public void search() {
        String product_id = "";
        String member_name = "";

        System.out.println("< 리뷰 검색 >");
        System.out.println("== 리뷰 리스트 ==");

        while (true) {
            System.out.printf("상품 번호: ");
            product_id = scanner.nextLine().trim();

            if (product_id.length() == 0) break;

            try {
                Integer.parseInt(product_id);
            } catch(NumberFormatException e) {
                System.out.println("숫자만 입력해주세요.\n");
                continue;
            }

            SecSql sql = new SecSql();

            sql.append("SELECT COUNT(*) > 0");
            sql.append("FROM `product`");
            sql.append("WHERE `product`.id = ?", product_id);

            if(!DBUtil.selectRowBooleanValue(Container.conn, sql)) {
                System.out.println("입력하신 상품 번호는 존재하지 않는 번호입니다.");
                System.out.println("다시 입력해주세요.\n");
                continue;
            }

            System.out.println("");

            break;
        }

        while (true) {
            System.out.printf("작성자 이름: ");
            member_name = scanner.nextLine().trim();

            if (member_name.length() == 0) break;

            SecSql sql = new SecSql();

            sql.append("SELECT COUNT(*) > 0");
            sql.append("FROM `member`");
            sql.append("WHERE `member`.member_name = ?", member_name);

            if(!DBUtil.selectRowBooleanValue(Container.conn, sql)) {
                System.out.println("입력하신 작성자 이름은 존재하지 않습니다.");
                System.out.println("다시 입력해주세요.\n");
                continue;
            }

            System.out.println("");

            break;
        }


        if (product_id.isEmpty() && member_name.isEmpty()) {
            System.out.println("검색어를 입력하지 않았습니다.");
            return;
        }

        boolean isMemberNameInputted = !member_name.isEmpty();
        boolean isProductIdInputted = !product_id.isEmpty();

        SecSql sql = new SecSql();
        sql.append("SELECT P.id AS '상품번호', P.product_name AS '상품이름', M.member_name AS '작성자', R.review AS '내용', R.grade AS '별점'");
        sql.append("FROM `review` AS R");
        sql.append("INNER JOIN `member` AS M");
        sql.append("ON R.`member_id` = M.id");
        sql.append("INNER JOIN `product` AS P");
        sql.append("ON R.`product_id` = P.id");
        sql.append("WHERE");
        if(isMemberNameInputted) sql.append("M.`member_name` = ?", member_name);
        if(isMemberNameInputted && isProductIdInputted) sql.append("AND");
        if(isProductIdInputted) sql.append("R.product_id = ?", product_id);

        List<Map<String, Object>> reviewList = DBUtil.selectRows(Container.conn, sql);

        if(reviewList.isEmpty()) {
            System.out.println("검색된 리뷰가 없습니다.");
            return;
        }

        System.out.println("<상품번호 / 상품이름 / 작성자 / 내용 / 별점>");
        System.out.println("-".repeat(50));
        for (Map<String, Object> reviewMap : reviewList) {
            System.out.printf("%2d / %s / %s / %s / %d\n", reviewMap.get("상품번호"), reviewMap.get("상품이름"), reviewMap.get("작성자"), reviewMap.get("내용"), reviewMap.get("별점"));
        }

//
//        System.out.println("");
//
//        String reviewName;
//
//        while (true) {
//            System.out.printf("검색어를 입력해주세요: ");
//            reviewName = scanner.nextLine().trim();
//
//            boolean isInReviewList = false;
//
//            for (Map<String, Object> reviewMap : reviewList) {
//                if (reviewName.equals(reviewMap.get("review"))) isInReviewList = true;
//            }
//
//            if (isInReviewList) break;
//
//            System.out.println("다시 입력해주세요");
//        }
//
//        sql = new SecSql();
//        sql.append("SELECT * ");
//        sql.append("FROM review");
//        sql.append("WHERE review = ?;", reviewName);
//
//        List<Map<String, Object>> searchedReviewMapList = DBUtil.selectRows(Container.conn, sql);
//
//        if(searchedReviewMapList.isEmpty()) {
//            System.out.println("검색 결과가 존재하지 않습니다.");
//            Container.session.setSessionReview(null);
//            return;
//        }
//
//        List<Review> searchedReivewList = new ArrayList<>();
//
//        for (Map<String, Object> searchedReviewMap : searchedReviewMapList) {
//            searchedReivewList.add(new Review(searchedReviewMap));
//        }
//
//        System.out.printf("%s에 대한 검색 결과...\n\n", reviewName);
//        System.out.println("상품번호 / 리뷰내용 / 별점");
//        System.out.println("-".repeat(60));
//        for (Review review : searchedReivewList) {
//            System.out.printf("%d / %s / %d \n", review.getProduct_id(), review.getReview(), review.getGrade());
//        }
//
//        System.out.printf("세부정보를 확인 할 상품번호를 입력해주세요: ");
//        int product_id = scanner.nextInt();
//        scanner.nextLine();
//        Review searchedReview = null;
//        for (Review review : searchedReivewList) {
//            if (review.getId() == product_id) {
//                searchedReview = review;
//            }
//        }
//
//        if (searchedReview == null) {
//            System.out.println("검색되지 않은 상품번호입니다.");
//            return;
//        }
//
//        Container.session.setSessionReview(searchedReview);
    }
    public void showDetail() {
        Review review = Container.session.getSessionReview();
        if (review == null) return;
        System.out.printf("번호 : %d\n", review.getId());
        // System.out.printf("등록일 : %s\n", article.regDate);
//    System.out.printf("작성자 : %s\n", article.extra__writerName);
        System.out.printf("작성자 : %d\n", review.getMember_id());
        System.out.printf("상품번호 : %d\n", review.getProduct_id());
        System.out.printf("리뷰내용 : %s\n", review.getReview());
        System.out.printf("별점 : %d\n", review.getGrade());
    }

    public void modify() {
        if (Container.session.isLogined() == false) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }

        System.out.printf("수정할 상품 번호을 입력해주세요: ");
        int product_id = scanner.nextInt();
        scanner.nextLine();

        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM review");
        sql.append("WHERE product_id = ?;", product_id);

        Container.session.setSessionReview(new Review(DBUtil.selectRow(Container.conn, sql)));

        System.out.println("수정할 리뷰 번호의 기존정보는 아래와 같습니다.");
        showDetail();

        System.out.println("=".repeat(50)); // if 문 통해서 수정할 내용을 입력한 정보만 수정이 됨.
        System.out.println("수정할 내용을 입력해주세요.");
        System.out.printf("리뷰 내용: ");
        String review = scanner.nextLine().trim();
        if (review.length() == 0) {
            review = Container.session.getSessionReview().getReview();
        }

        System.out.printf("별점: ");
        int grade = scanner.nextInt();
        scanner.nextLine();
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
        scanner.nextLine();

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
        sql.append("WHERE review.id = ?;", review_id);

        DBUtil.delete(Container.conn, sql);

        System.out.printf("%d번 리뷰가 삭제되었습니다.\n", review_id);
    }


}