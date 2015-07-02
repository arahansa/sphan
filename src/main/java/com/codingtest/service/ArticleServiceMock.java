package com.codingtest.service;

import com.codingtest.dto.Page;
import com.codingtest.exception.NotFoundException;
import com.codingtest.model.Article;
import com.codingtest.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by arahansa on 2015-06-28.
 */
@Service
public class ArticleServiceMock {

    public static final String MOCK = "mock";

    @Autowired
    @Qualifier(MOCK)
    ArticleRepository mockRepository;
    public void setMockRepository(ArticleRepository mockRepository) {
        this.mockRepository = mockRepository;
    }

    public Collection<Page> getList(){
        Collection<Article> articles = mockRepository.getList();
        List<Page> pages = new ArrayList<>();
        for (Article a : articles){
            Page page = new Page(a.getId(),a.getSubject(), a.getNick());
            pages.add(page);
        }
        return pages;
    }

    public Article getArticle(Long id) throws NotFoundException {
        Article article =  mockRepository.getArticle(id);
        if(article == null){
            throw new NotFoundException();
        }
        return article;
    }

    public Long saveArticle(Article article){
        mockRepository.save(article);
        return article.getId();
    }

    public Article updateArticle(Article article) throws NotFoundException {
        if(getArticle(article.getId())!=null){
            mockRepository.update(article);
        }
        return article;
    }

    public void deleteArticle(Long id) throws NotFoundException {
        if(getArticle(id)!=null){
            mockRepository.delete(id);
        }

    }



}
