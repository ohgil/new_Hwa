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
<<<<<<< HEAD

    System.out.println("== 게시물 등록 ==");
    System.out.printf("제목 : ");
    String title = Container.scanner.nextLine();
    System.out.printf("내용 : ");
    String body = Container.scanner.nextLine();

    int memberId = Container.session.loginedMemberId;
    int id = productService.write(memberId, title, body);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }
  public void showList() {
    System.out.println("== 게시물 리스트 ==");
=======
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
>>>>>>> f90810f (상품등록부분까지)
    int page = rq.getIntParam("page", 1);
    String searchKeyword = rq.getParam("searchKeyword", "");
    int pageItemCount = 10;

    // 임시
    pageItemCount = 5;

<<<<<<< HEAD
    List<Product> products = productService.getForPrintProductById(page, pageItemCount, searchKeyword);

    if (products.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.println("번호 / 작성날짜 / 작성자 / 제목");

    for (Product product : products) {
      System.out.printf("%d / %s / %s / %s\n", product.id, product.regDate, product.extra__writerName, product.title);
=======
    List<Product> articles = productService.getForPrintArticleById(page, pageItemCount, searchKeyword);

    if (articles.isEmpty()) {
      System.out.println("상품이 존재하지 않습니다.");
      return;
    }

    System.out.println("등록일 / 작성자 / 상품명 / 상품브랜드 / 상품용량 / 상품가격 / 상품설명");

    for (Product article : articles) {
      System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s\n", article.id, article.regDate, article.extra__writerName, article.product_name, article.product_brand, article.product_capacity, article.product_price, article.product_explanation);
>>>>>>> f90810f (상품등록부분까지)
    }
  }

  public void showDetail() {
    int id = rq.getIntParam("id", 0);

    if (id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

<<<<<<< HEAD
    Product product = productService.getProductById(id);

    if (product == null) {
      System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("번호 : %d\n", product.id);
    System.out.printf("등록날짜 : %s\n", product.regDate);
    System.out.printf("수정날짜 : %s\n", product.updateDate);
    System.out.printf("작성자 : %s\n", product.extra__writerName);
    System.out.printf("조회수 : %d\n", product.hit);
    System.out.printf("제목 : %s\n", product.title);
    System.out.printf("내용 : %s\n", product.body);
=======
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
>>>>>>> f90810f (상품등록부분까지)
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

<<<<<<< HEAD
    Product product = productService.getProductById(id);

    boolean productExists = productService.productExists(id);

    if (productExists == false) {
      System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
      return;
    }

    if(product.memberId != Container.session.loginedMemberId) {
=======
    Product article = productService.getArticleById(id);

    boolean articleExists = productService.articleExists(id);

    if (articleExists == false) {
      System.out.printf("%d번 상품은 존재하지 않습니다.\n", id);
      return;
    }

    if(article.member_id != Container.session.loginedMemberId) {
>>>>>>> f90810f (상품등록부분까지)
      System.out.println("권한이 없습니다");
      return;
    }

<<<<<<< HEAD
    System.out.printf("새 제목 : ");
    String title = Container.scanner.nextLine();
    System.out.printf("새 내용 : ");
    String body = Container.scanner.nextLine();

    productService.update(id, title, body);
=======
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
>>>>>>> f90810f (상품등록부분까지)

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

<<<<<<< HEAD
    Product product = productService.getProductById(id);

    boolean productExists = productService.productExists(id);

    if (productExists == false) {
=======
    Product article = productService.getArticleById(id);

    boolean articleExists = productService.articleExists(id);

    if (articleExists == false) {
>>>>>>> f90810f (상품등록부분까지)
      System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
      return;
    }

<<<<<<< HEAD
    if(product.memberId != Container.session.loginedMemberId) {
=======
    if(article.member_id != Container.session.loginedMemberId) {
>>>>>>> f90810f (상품등록부분까지)
      System.out.println("권한이 없습니다");
      return;
    }

    productService.delete(id);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }

}
