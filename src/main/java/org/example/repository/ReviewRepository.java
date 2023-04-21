package org.example.repository;

import org.example.Container;
import org.example.dto.Article;
import org.example.dto.Review;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewRepository {
    public int write(int member_id, String review, double grade) {
        SecSql sql = new SecSql();

        sql.append("INSERT INTO review");
        sql.append(", member_id = ?", member_id);
        sql.append(", review = ?", review);
        sql.append(", `grade` = ?", grade);

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

    public void update(int id, String review, double grade) {
        SecSql sql = new SecSql();

        sql.append("UPDATE article");
        sql.append("SET review = ?", review);
        sql.append(", `grade` = ?", grade);
        sql.append("WHERE id = ?", id);

        DBUtil.update(Container.conn, sql);
    }

    public Review getReviewById(int id) {
        SecSql sql = new SecSql();

        sql.append("SELECT A.*");
        sql.append(", M.name AS extra__writerName");
        sql.append("FROM review AS A");
        sql.append("INNER JOIN member AS M");
        sql.append("ON A.member_id = M.id");
        sql.append("WHERE A.id = ?", id);

        Map<String, Object> reviewMap = DBUtil.selectRow(Container.conn, sql);

        if (reviewMap.isEmpty()) {
            return null;
        }

        return new Review(reviewMap);
    }

    public List<Review> getReviews(Map<String, Object> args, String searchKeyword) {
        SecSql sql = new SecSql();

        if(args.containsKey("searchKeyword")) {
            searchKeyword = (String) args.get("searchKeyword");
        }

        int limitFrom = -1;
        int limitTake = -1;

        if(args.containsKey("limitFrom")) {
            limitFrom = (int) args.get("limitFrom");
        }

        if(args.containsKey("limitTake")) {
            limitTake = (int) args.get("limitTake");
        }

        sql.append("SELECT A.*, M.name AS extra__writerName");
        sql.append("FROM review AS A");
        sql.append("INNER JOIN member AS M");
        sql.append("ON A.member_id = M.id");
        if(searchKeyword.length() > 0) {
            sql.append("WHERE A.review LIKE CONCAT('%', ?, '%')", searchKeyword);
        }
        sql.append("ORDER BY A.id DESC");

        if(limitFrom != -1) {
            sql.append("LIMIT ?, ?", limitFrom, limitTake);
        }

        List<Review> reviews = new ArrayList<>();

        List<Map<String, Object>> reviewListMap = DBUtil.selectRows(Container.conn, sql);

        for (Map<String, Object> reviewMap : reviewListMap) {
            reviews.add(new Review(reviewMap));
        }

        return reviews;
    }
    }
