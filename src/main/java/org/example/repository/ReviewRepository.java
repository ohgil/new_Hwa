package org.example.repository;

import org.example.Container;
import org.example.dto.Review;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReviewRepository {
    public int write(int product_id, String review, int grade) {
        SecSql sql = new SecSql();

        sql.append("INSERT INTO review");
        sql.append("SET product_id = ?", product_id);
        sql.append(", review = ?", review);
        sql.append(", grade = ?", grade);

        int id = DBUtil.insert(Container.conn, sql);
        return id;
    }

    public boolean reviewExists(int id) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM review");
        sql.append("WHERE id = ?", id);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }

    public void delete(int id) {
        SecSql sql = new SecSql();

        sql.append("DELETE FROM review");
        sql.append("WHERE id = ?", id);

        DBUtil.delete(Container.conn, sql);
    }


    public void update(int product_id, String review, int grade) {
        SecSql sql = new SecSql();

        sql.append("UPDATE review");
        sql.append("SET product_id = ?", product_id);
        sql.append(", review = ?", review);
        sql.append(", grade = ?", grade);
        sql.append("WHERE id = ?", product_id);

        DBUtil.update(Container.conn, sql);
    }

    public static Review getReviewById(int id) {
        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append(" FROM review");
        sql.append(" WHERE review.product_id = product.id");



    /*SELECT product.id, care.id, `type`.id, product.product_name, product.product_brand, product.product_capacity, product.product_price, product.product_explanation
    from product
    JOIN `care` on product.id = `care`.id;
    JOIN `type` on product.id = `type`.id;*/

    /*sql.append("SELECT A.*");
    sql.append(", M.name AS extra__writerName");
    sql.append("FROM article AS A");
    sql.append("INNER JOIN member AS M");
    sql.append("ON A.memberId = M.id");
    sql.append("WHERE A.id = ?", id);*/

        Map<String, Object> reviewMap = DBUtil.selectRow(Container.conn, sql);

        if (reviewMap.isEmpty()) {
            return null;
        }

        return new Review(reviewMap);
    }

//  public List<Product> getArticles(Map<String, Object> args, String searchKeyword) {
//    SecSql sql = new SecSql();
//
//    if(args.containsKey("searchKeyword")) {
//      searchKeyword = (String) args.get("searchKeyword");
//    }
//
//    int limitFrom = -1;
//    int limitTake = -1;
//
//    if(args.containsKey("limitFrom")) {
//      limitFrom = (int) args.get("limitFrom");
//    }
//
//    if(args.containsKey("limitTake")) {
//      limitTake = (int) args.get("limitTake");
//    }
//
//    sql.append("SELECT A.*, M.`member_name` AS extra__writerName");
//    sql.append("FROM product AS A");
//    sql.append("INNER JOIN member AS M");
//    sql.append("ON A.memberId = M.id");
//    if(searchKeyword.length() > 0) {
//      sql.append("WHERE A.title LIKE CONCAT('%', ?, '%')", searchKeyword);
//    }
//    sql.append("ORDER BY A.id DESC");
//
//    if(limitFrom != -1) {
//      sql.append("LIMIT ?, ?", limitFrom, limitTake);
//    }
//
//    List<Product> products = new ArrayList<>();
//
//    List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);
//
//    for (Map<String, Object> articleMap : articleListMap) {
//      products.add(new Product(articleMap));
//    }
//
//    return products;
//  }


    public void increaseHit(int id) {
        SecSql sql = new SecSql();

        sql.append("UPDATE article");
        sql.append("SET hit = hit + 1");
        sql.append("WHERE id = ?", id);

        DBUtil.update(Container.conn, sql);
    }


    public boolean reviewNameExists(String reviewtName) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM review");
        sql.append("WHERE `review`.`product_id` Like CONCAT('%', ?, '%')", reviewtName);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }
}




