package org.example.service;

import org.example.Container;
import org.example.dto.Article;
import org.example.repository.ArticleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleService {
  private ArticleRepository articleRepository;
  public ArticleService() {
    articleRepository = Container.articleRepository;
  }

  public int write(int memberId, String title, String body) {
    return articleRepository.write(memberId, title, body);
  }

  public boolean articleExists(int id) {
    return articleRepository.articleExists(id);
  }

  public void delete(int id) {
    articleRepository.delete(id);
  }

  public void update(int id, String title, String body) {
    articleRepository.update(id, title, body);
  }

  public Article getArticleById(int id) {
    return articleRepository.getArticleById(id);
  }

  public void increaseHit(int id) {
    articleRepository.increaseHit(id);
  }

  public List<Article> getForPrintArticleById(int page, int pageItemCount, String searchKeyword) {
    int limitFrom = (page - 1) * pageItemCount;
    int limitTake = pageItemCount;

    Map<String, Object> args = new HashMap<>();
    args.put("limitFrom", limitFrom);
    args.put("limitTake", limitTake);
    return articleRepository.getArticles(args, searchKeyword);
  }
}
