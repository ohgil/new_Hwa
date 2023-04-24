package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
  public void run() {
    Container.scanner = new Scanner(System.in);
    Container.init();

    while (true) {
      System.out.printf("명령어) ");
      String cmd = Container.scanner.nextLine();

      Container.rq = new Rq(cmd);

      // DB 연결
      Connection conn = null;

      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException e) {
        System.out.println("예외 : MySQL 드라이버 로딩 실패");
        System.out.println("프로그램을 종료합니다.");
        break;
      }

      String url = "jdbc:mysql://127.0.0.1:3306/Hwa?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull";

      try {
        conn = DriverManager.getConnection(url, "root", "");

        Container.conn = conn;

        // action 메서드 실행
        action(Container.rq, cmd);

      } catch (SQLException e) {
        System.out.println("예외 : MySQL 드라이버 로딩 실패");
        System.out.println("프로그램을 종료합니다.");
        break;
      } finally {
        try {
          if (conn != null && !conn.isClosed()) {
            conn.close();
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      // DB 연결 끝
    }
    Container.scanner.close();
  }

  private void action(Rq rq, String cmd) {
    if (rq.getUrlPath().equals("회원가입")) {
      Container.memberController.join();
    } else if (rq.getUrlPath().equals("로그인")) {
      Container.memberController.login();
    } else if (rq.getUrlPath().equals("로그아웃")) {
      Container.memberController.logout();
    } else if (rq.getUrlPath().equals("내정보")) {
      Container.memberController.whoami();
    } else if (rq.getUrlPath().equals("상품등록")) {
      Container.productController.write();
    } else if (rq.getUrlPath().equals("상품검색")) {
      Container.productController.search();
      Container.productController.showDetail();
      return;
    } else if(rq.getUrlPath().equals("상품설명")) {
      Container.productController.showDetail();
    } else if (rq.getUrlPath().equals("상품수정")) {
      Container.productController.modify();
      return;
    } else if (rq.getUrlPath().equals("상품삭제")) {
      Container.productController.delete();
    } else if (rq.getUrlPath().equals("리뷰등록")) { // 작업해주세요 승열이형
      Container.reviewController.write();
    } else if (rq.getUrlPath().equals("리뷰검색")) {// 작업해주세요 승열이형
      Container.reviewController.showList();
    } else if (rq.getUrlPath().equals("리뷰수정")) {// 작업해주세요 승열이형
      Container.reviewController.modify();
    } else if (rq.getUrlPath().equals("리뷰삭제")) {// 작업해주세요 승열이형
      Container.reviewController.delete();
    } else if (cmd.equals("종료")) {
      System.out.println("시스템 종료");
      System.exit(0);
    } else {
      System.out.println("명령어를 확인해주세요.");
    }
    return;
  }

}
