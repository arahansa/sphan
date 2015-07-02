package service;

import com.codingtest.Application;
import com.codingtest.dto.Page;
import com.codingtest.exception.NotFoundException;
import com.codingtest.model.Article;
import com.codingtest.repository.ArticleRepository;
import com.codingtest.service.ArticleServiceMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by arahansa on 2015-06-29.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ArticleServiceTest {
    public static final String ARAHANSA = "arahansa";
    public static final String SUBJECT = "subject";
    public static final String HELLO_WORLD = "hello world";
    public static final String UPDATED_ARTICLE = "updatedArticle";
    public static final String UPDATE_CONTENT = "updateContent";

    @Autowired
    ArticleServiceMock service;

    @Autowired
    @Qualifier("mock")
    ArticleRepository repository;
    Article[] basicArticles = new Article[5];

    @Before
    public void setup(){
        repository.deleteAll();
        for (int i=0;i<5;i++){
            basicArticles[i] = new Article(Long.valueOf(i), ARAHANSA +i, SUBJECT +i, HELLO_WORLD +i);
           service.saveArticle(basicArticles[i]);
        }
        checkArticleSize(5);
    }

    @Test
    public void pages() throws Exception{
        Collection<Page> list = service.getList();
        System.out.println(list);
        int i=0;
        for (Page p: list){
            checkWholePageFields(p,basicArticles[i] );
            i++;
        }
    }

    @Test
    public void getArticle() throws Exception{
        Article article = service.getArticle(1L);
        checkWholeArticleFields(article, basicArticles[1]);
    }

    @Test
    public void update() throws Exception {
        Article getArticle = service.getArticle(2L);
        getArticle.setNick("arahansa2");
        getArticle.setContent("nihao");
        service.saveArticle(getArticle);
        Article updatedArticle = service.getArticle(2L);
        checkWholeArticleFields(getArticle, updatedArticle);
    }
    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        Article article = new Article(Long.valueOf(7), ARAHANSA +7, SUBJECT +7, HELLO_WORLD +7);
        service.saveArticle(article);
        service.deleteArticle(7L);
        service.getArticle(7L);
    }


    private void checkWholePageFields(Page pageExpected, Article articleReal){
        assertEquals("check Article id :", pageExpected.getId(), articleReal.getId());
        assertEquals("check Article nick :", pageExpected.getNick(), articleReal.getNick());
        assertEquals("check Article subject :", pageExpected.getSubject(), articleReal.getSubject());
    }

    private void checkWholeArticleFields(Article articleExpected, Article articleReal){
        assertEquals("check Article id :", articleExpected.getId(), articleReal.getId());
        assertEquals("check Article nick :", articleExpected.getNick(), articleReal.getNick());
        assertEquals("check Article subject :", articleExpected.getSubject(), articleReal.getSubject());
        assertEquals("check text:", articleExpected.getContent(), articleReal.getContent());
    }
    private void checkArticleSize(int sizeExpected){
        assertEquals("expected article size : ", sizeExpected, repository.count());
    }

}
