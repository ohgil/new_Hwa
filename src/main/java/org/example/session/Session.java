package org.example.session;

import org.example.dto.Member;
import org.example.dto.Product;
import org.example.dto.Review;

public class Session {
    public int loginedMemberId;
    public Member loginedMember;
    private Product sessionProduct;
    public Review sessionReview;

    public Session() {
        loginedMemberId = -1;
    }

    public boolean isLogined() {
        return loginedMemberId != -1;
    }

    public void login(Member member) {
        loginedMemberId = member.getId();
        loginedMember = member;
    }

    public void logout() {
        loginedMemberId = -1;
        loginedMember = null;
    }

    public Product getSessionProduct() {
        return sessionProduct;
    }

    public void setSessionProduct(Product sessionProduct) {
        this.sessionProduct = sessionProduct;
    }

    public Review getSessionReview() {
        return sessionReview;
    }

    public void setSessionReview(Review sessionReview) {
        this.sessionReview = sessionReview;
    }
}

// 이 클래스는 로그인한 회원 정보를 유지하고, 로그인 여부를 확인하며, 로그인 및 로그아웃을 처리하는 기능을 제공
