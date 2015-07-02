package com.codingtest.repository;

import com.codingtest.model.Article;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by arahansa on 2015-06-29.
 */
@Repository
public interface ArticleRepository {
    Collection<Article> getList();

    Article getArticle(Long id);

    void save(Article article);

    void update(Article article);

    void delete(Long id);

    void deleteAll();

    int count();
}
