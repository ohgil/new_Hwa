package org.example;

import org.example.controller.ArticleController;
import org.example.controller.MemberController;
import org.example.repository.ArticleRepository;
import org.example.repository.MemberRepository;
import org.example.service.ArticleService;
import org.example.service.MemberService;
import org.example.session.Session;

import java.sql.Connection;
import java.util.Scanner;

public class Container {

  public static ArticleRepository articleRepository;
  public static MemberRepository memberRepository;

  public static ArticleService articleService;
  public static MemberService memberService;

  public static ArticleController articleController;
  public static MemberController memberController;

  public static Scanner scanner;
  public static Session session;
  public static Connection conn;
  public static Rq rq;

  public static void init() {
    articleRepository = new ArticleRepository();
    memberRepository = new MemberRepository();

    articleService = new ArticleService();
    memberService = new MemberService();

    articleController = new ArticleController();
    memberController = new MemberController();

    scanner = new Scanner(System.in);
    session = new Session();
  }

}
