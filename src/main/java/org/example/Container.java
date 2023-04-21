package org.example;

import org.example.controller.ArticleController;
import org.example.controller.MemberController;
import org.example.controller.ReviewController;
import org.example.repository.ArticleRepository;
import org.example.repository.MemberRepository;
import org.example.repository.ReviewRepository;
import org.example.service.ArticleService;
import org.example.service.MemberService;
import org.example.service.ReviewService;
import org.example.session.Session;

import java.sql.Connection;
import java.util.Scanner;

public class Container {

  public static ArticleRepository articleRepository;
  public static MemberRepository memberRepository;
  public static ReviewRepository reviewRepository;

  public static ArticleService articleService;
  public static MemberService memberService;
  public static ReviewService reviewService;
  public static ArticleController articleController;
  public static MemberController memberController;
  public static ReviewController reviewController;
  public static Scanner scanner;
  public static Session session;
  public static Connection conn;
  public static Rq rq;

  public static void init() {
    articleRepository = new ArticleRepository();
    memberRepository = new MemberRepository();
    reviewRepository = new ReviewRepository();
    articleService = new ArticleService();
    memberService = new MemberService();
    reviewService = new ReviewService();
    articleController = new ArticleController();
    memberController = new MemberController();
    reviewController = new ReviewController();
    scanner = new Scanner(System.in);
    session = new Session();
  }

}
