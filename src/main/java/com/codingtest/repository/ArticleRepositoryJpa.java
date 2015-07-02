package com.codingtest.repository;

import com.codingtest.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by arahansa on 2015-06-29.
 */
@Repository
@Qualifier("jpa")
public class ArticleRepositoryJpa implements ArticleRepository {

    @Autowired
    ArticleJpaInterface repository;

    @Override
    public Collection<Article> getList() {
        return repository.findAll();
    }

    @Override
    public Article getArticle(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void save(Article article) {
        repository.save(article);
    }

    @Override
    public void update(Article article) {
        repository.save(article);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public int count() {
        return (int) repository.count();
    }
}
