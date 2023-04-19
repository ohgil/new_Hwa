package org.example.repository;

import org.example.Container;
import org.example.dto.Article;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleRepository {
  public int write(int memberId, String title, String body) {
    SecSql sql = new SecSql();

    sql.append("INSERT INTO article");
    sql.append(" SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", memberId = ?", memberId);
    sql.append(", title = ?", title);
    sql.append(", `body` = ?", body);

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

    sql.append("DELETE FROM article");
    sql.append("WHERE id = ?", id);

    DBUtil.delete(Container.conn, sql);
  }

  public void update(int id, String title, String body) {
    SecSql sql = new SecSql();

    sql.append("UPDATE article");
    sql.append("SET updateDate = NOW()");
    sql.append(", title = ?", title);
    sql.append(", `body` = ?", body);
    sql.append("WHERE id = ?", id);

    DBUtil.update(Container.conn, sql);
  }

  public Article getArticleById(int id) {
    SecSql sql = new SecSql();

    sql.append("SELECT A.*");
    sql.append(", M.name AS extra__writerName");
    sql.append("FROM article AS A");
    sql.append("INNER JOIN member AS M");
    sql.append("ON A.memberId = M.id");
    sql.append("WHERE A.id = ?", id);

    Map<String, Object> articleMap = DBUtil.selectRow(Container.conn, sql);

    if (articleMap.isEmpty()) {
      return null;
    }

    return new Article(articleMap);
  }

  public List<Article> getArticles(Map<String, Object> args, String searchKeyword) {
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
    sql.append("FROM article AS A");
    sql.append("INNER JOIN member AS M");
    sql.append("ON A.memberId = M.id");
    if(searchKeyword.length() > 0) {
      sql.append("WHERE A.title LIKE CONCAT('%', ?, '%')", searchKeyword);
    }
    sql.append("ORDER BY A.id DESC");

    if(limitFrom != -1) {
      sql.append("LIMIT ?, ?", limitFrom, limitTake);
    }

    List<Article> articles = new ArrayList<>();

    List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);

    for (Map<String, Object> articleMap : articleListMap) {
      articles.add(new Article(articleMap));
    }

    return articles;
  }

  public void increaseHit(int id) {
    SecSql sql = new SecSql();

    sql.append("UPDATE article");
    sql.append("SET hit = hit + 1");
    sql.append("WHERE id = ?", id);

    DBUtil.update(Container.conn, sql);
  }
}
