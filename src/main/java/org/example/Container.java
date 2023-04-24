package org.example;

import org.example.controller.ProductController;
import org.example.controller.MemberController;
import org.example.controller.ReviewController;
import org.example.repository.ProductRepository;
import org.example.repository.MemberRepository;
import org.example.repository.ReviewRepository;
import org.example.service.ProductService;
import org.example.service.MemberService;
import org.example.service.ReviewService;
import org.example.session.Session;

import java.sql.Connection;
import java.util.Scanner;

public class Container {

  public static ProductRepository productRepository;
  public static MemberRepository memberRepository;
  public static ReviewRepository reviewRepository;

  public static ProductService productService;
  public static MemberService memberService;
  public static ReviewService reviewService;

  public static ProductController productController;
  public static MemberController memberController;
  public static ReviewController reviewController;

  public static Scanner scanner;
  public static Session session;
  public static Connection conn;
  public static Rq rq;

  public static void init() {
    productRepository = new ProductRepository();
    memberRepository = new MemberRepository();
    reviewRepository = new ReviewRepository();

    productService = new ProductService();
    memberService = new MemberService();
    reviewService = new ReviewService();

    productController = new ProductController();
    memberController = new MemberController();
    reviewController = new ReviewController();

    scanner = new Scanner(System.in);
    session = new Session();
  }

}
