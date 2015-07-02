package com.codingtest.repository;

import com.codingtest.model.Article;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by arahansa on 2015-06-28.
 */

@Repository
@Qualifier("mock")
public class ArticleRepositoryMock implements ArticleRepository {
    private Map<Long, Article> mockMap;

    @PostConstruct
    public void init(){
        mockMap= new ConcurrentHashMap<>();
    }

    @Override
    public Collection<Article> getList(){
        return mockMap.values();
    }

    @Override
    public Article getArticle(Long id){
        return mockMap.get(id);
    }

    @Override
    public void save(Article article){
        if(article.getId()==null){
            Long max = mockMap.keySet().stream().max(Long::compareTo).orElse(0L)+1;
            article.setId(max);
        }
        mockMap.put(article.getId(), article);
    }

    @Override
    public void update(Article article){
        mockMap.put(article.getId(), article);
    }
    @Override
    public void delete(Long id){ mockMap.remove(id); }

    @Override
    public void deleteAll(){ mockMap.clear(); }
    @Override
    public int count(){ return mockMap.size(); }







}
