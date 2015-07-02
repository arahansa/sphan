package com.codingtest.repository;

import com.codingtest.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by arahansa on 2015-06-29.
 */

public interface ArticleJpaInterface extends JpaRepository<Article, Long> {

}
