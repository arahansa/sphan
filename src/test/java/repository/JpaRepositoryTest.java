package repository;

import com.codingtest.Application;
import com.codingtest.model.Article;

import com.codingtest.repository.ArticleRepositoryJpa;
import org.hibernate.Hibernate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by arahansa on 2015-06-29.
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class JpaRepositoryTest {
    public static final String ARAHANSA = "arahansa";
    public static final String SUBJECT = "subject";
    public static final String HELLO_WORLD = "hello world";
    public static final String UPDATED_ARTICLE = "updatedArticle";
    public static final String UPDATE_CONTENT = "updateContent";

    @Autowired
    @Qualifier("jpa")
    ArticleRepositoryJpa repository;
    Article[] basicArticles = new Article[5];
    @Before
    public void setup(){
        repository.deleteAll();
        for (int i=0;i<5;i++){
            basicArticles[i] = new Article( ARAHANSA +i, SUBJECT +i, HELLO_WORLD +i);
            repository.save(basicArticles[i]);
        }
        checkArticleSize(5);
    }

    @Test
    public void saveAndRead() throws Exception{
        Article newArticle = new Article(ARAHANSA, SUBJECT, HELLO_WORLD);
        repository.save(newArticle);
        checkArticleSize(6);
        Article getArticle = getJustoneArticle();
        checkWholeArticleFields(getArticle, newArticle);
    }

    @Test
    public void update() throws  Exception {
        checkArticleSize(5);
        Article getArticle = getJustoneArticle();
        System.out.println("msg~"+getArticle);
        getArticle.setNick("arahansa2");
        repository.update(getArticle);
        Article getUpdatedArticle = repository.getArticle(getArticle.getId());
        System.out.println("msg~"+getUpdatedArticle);
        checkWholeArticleFields(getArticle, getUpdatedArticle);
    }

    @After
    public void deleteArticle() throws Exception {
        repository.deleteAll();
        checkArticleSize(0);
        Article deletedArticle = new Article(ARAHANSA, SUBJECT, HELLO_WORLD);
        repository.save(deletedArticle);
        checkArticleSize(1);
        repository.delete(getJustoneArticle().getId());
        checkArticleSize(0);
    }

    private Article getJustoneArticle() {
        List<Article> list = new ArrayList<>();
        list.addAll(repository.getList());
        return list.get(list.size()-1);

    }
    private void checkWholeArticleFields(Article articleExpected, Article articleReal){

        assertEquals("check Article nick :", articleExpected.getNick(), articleReal.getNick());
        assertEquals("check Article subject :", articleExpected.getSubject(), articleReal.getSubject());
        assertEquals("check text:", articleExpected.getContent(), articleReal.getContent());
    }
    private void checkArticleSize(int sizeExpected){
        assertEquals("expected article size : ", sizeExpected, repository.count());
    }
}
