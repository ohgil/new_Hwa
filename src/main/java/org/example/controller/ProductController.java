package org.example.controller;


import org.example.Container;
import org.example.dto.Product;
import org.example.service.ProductService;

import java.util.List;

import static org.example.Container.rq;

public class ProductController {

  private ProductService productService;

  public ProductController() {
    productService = Container.productService;
  }

  public void write() {
    if(Container.session.isLogined() == false) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }
// care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation
    System.out.println("== 상품 등록 ==");
    System.out.printf("상품명  : ");
    String product_name = Container.scanner.nextLine();
    System.out.printf("상품브랜드  : ");
    String product_brand = Container.scanner.nextLine();
    System.out.printf("상품용량  : ");
    String product_capacity = Container.scanner.nextLine();
    System.out.printf("상품가격  : ");
    String product_price = Container.scanner.nextLine();
    System.out.printf("상품설명  : ");
    String product_explanation = Container.scanner.nextLine();

    int member_email = Container.session.loginedMemberId;
    int id = productService.write(product_name, product_brand, product_capacity, product_price, product_explanation);

    System.out.printf("%d번 상품이 등록되었습니다.\n", id);
  }
  public void showList() {
    System.out.println("== 상품 리스트 ==");
    int page = rq.getIntParam("page", 1);
    String searchKeyword = rq.getParam("searchKeyword", "");
    int pageItemCount = 10;

    // 임시
    pageItemCount = 5;

    List<Product> articles = productService.getForPrintArticleById(page, pageItemCount, searchKeyword);

    if (articles.isEmpty()) {
      System.out.println("상품이 존재하지 않습니다.");
      return;
    }

    System.out.println("등록일 / 작성자 / 상품명 / 상품브랜드 / 상품용량 / 상품가격 / 상품설명");

    for (Product article : articles) {
      System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s\n", article.id, article.regDate, article.extra__writerName, article.product_name, article.product_brand, article.product_capacity, article.product_price, article.product_explanation);
    }
  }

  public void showDetail() {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    productService.increaseHit(id);
    Product article = productService.getArticleById(id);

    if (article == null) {
      System.out.printf("%d번 상품은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("번호 : %d\n", article.id);
   // System.out.printf("등록일 : %s\n", article.regDate);
    System.out.printf("작성자 : %s\n", article.extra__writerName);
    System.out.printf("상품명 : %s\n", article.product_name);
    System.out.printf("상품브랜드 : %d\n", article.product_brand);
    System.out.printf("상품용량 : %s\n", article.product_capacity);
    System.out.printf("상품가격 : %s\n", article.product_price);
    System.out.printf("상품설명 : %s\n", article.product_explanation);
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

    Product article = productService.getArticleById(id);

    boolean articleExists = productService.articleExists(id);

    if (articleExists == false) {
      System.out.printf("%d번 상품은 존재하지 않습니다.\n", id);
      return;
    }

//    if(article.member_id != Container.session.loginedMemberId) {
//      System.out.println("권한이 없습니다");
//      return;
//    }

    System.out.printf("상품명  : ");
    String product_name = Container.scanner.nextLine();
    System.out.printf("상품브랜드  : ");
    String product_brand = Container.scanner.nextLine();
    System.out.printf("상품용량  : ");
    String product_capacity = Container.scanner.nextLine();
    System.out.printf("상품가격  : ");
    String product_price = Container.scanner.nextLine();
    System.out.printf("상품설명  : ");
    String product_explanation = Container.scanner.nextLine();
    productService.update(product_name, product_brand, product_capacity, product_price, product_explanation);

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
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

    System.out.printf("== %d번 게시글 삭제 ==\n", id);

    Product article = productService.getArticleById(id);

    boolean articleExists = productService.articleExists(id);

    if (articleExists == false) {
      System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
      return;
    }

//    if(article.member_id != Container.session.loginedMemberId) {
//      System.out.println("권한이 없습니다");
//      return;
//    }

    productService.delete(id);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }

}
