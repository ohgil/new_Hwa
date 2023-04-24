package org.example.repository;

import org.example.Container;
import org.example.dto.Product;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    public int write(String product_name, String product_brand, String product_capacity, String product_price, String product_explanation) {
        SecSql sql = new SecSql();

        sql.append("INSERT INTO product");
        sql.append("SET product_name = ?", product_name);
        sql.append(", product_brand = ?", product_brand);
        sql.append(", product_capacity = ?", product_capacity);
        sql.append(", `product_price` = ?", product_price);
        sql.append(", product_explanation = ?", product_explanation);

        int id = DBUtil.insert(Container.conn, sql);
        return id;
    }

    public boolean articleExists(int id) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM article");
        sql.append("WHERE id = ?", id);

        return DBUtil.selectRowBooleanValue(Container.conn, sql);
    }

    public void delete(int id) {
        SecSql sql = new SecSql();

        sql.append("DELETE FROM product");
        sql.append("WHERE id = ?", id);

        DBUtil.delete(Container.conn, sql);
    }

    public void update(String product_name, String product_brand, String product_capacity, String product_price, String product_explanation) {
        SecSql sql = new SecSql();

        sql.append("UPDATE product");
        sql.append("SET product_name = ?", product_name);
        sql.append(", product_brand = ?", product_brand);
        sql.append(", product_capacity = ?", product_capacity);
        sql.append(", `product_price` = ?", product_price);
        sql.append(", product_explanation = ?", product_explanation);

        DBUtil.update(Container.conn, sql);
    }

    public Product getProductById(int id) {
        SecSql sql = new SecSql();

        sql.append("SELECT product.id, care.id, `type`.id, product.product_name, product.product_brand, product.product_capacity, product.product_price, product.product_explanation");
        sql.append(" FROM product");
        sql.append(" JOIN `care` on product.care_id = `care`.id");
        sql.append(" JOIN `type` on product.type_id = `type`.id");


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

        Map<String, Object> articleMap = DBUtil.selectRow(Container.conn, sql);

        if (articleMap.isEmpty()) {
            return null;
        }

        return new Product(articleMap);
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
}