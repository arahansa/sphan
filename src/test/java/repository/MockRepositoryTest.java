package repository;

import com.codingtest.Application;
import com.codingtest.model.Article;
import com.codingtest.repository.ArticleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
/**
 * Created by arahansa on 2015-06-29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MockRepositoryTest {

    public static final String ARAHANSA = "arahansa";
    public static final String SUBJECT = "subject";
    public static final String HELLO_WORLD = "hello world";
    public static final String UPDATED_ARTICLE = "updatedArticle";
    public static final String UPDATE_CONTENT = "updateContent";

    @Autowired
    @Qualifier("mock")
    ArticleRepository repository;
    Article[] basicArticles = new Article[5];

    @Before
    public void setup(){
        repository.deleteAll();
        for (int i=0;i<5;i++){
            basicArticles[i] = new Article(Long.valueOf(i), ARAHANSA +i, SUBJECT +i, HELLO_WORLD +i);
            repository.save(basicArticles[i]);
        }
        checkArticleSize(5);
    }

    @Test
    public void saveAndRead() throws Exception{
        Article newArticle = new Article(6L, ARAHANSA, SUBJECT, HELLO_WORLD);
        repository.save(newArticle);
        checkArticleSize(6);
        Article getArticle = repository.getArticle(newArticle.getId());
        checkWholeArticleFields(getArticle, newArticle);
        for (int i=0;i<5;i++){
            getArticle = repository.getArticle(Long.valueOf(i));
            checkWholeArticleFields(getArticle, basicArticles[i]);
        }
    }

    @Test
    public void update() throws  Exception {
        checkArticleSize(5);
        Article updatedArticle = new Article(1L, ARAHANSA, UPDATED_ARTICLE, UPDATE_CONTENT);
        repository.update(updatedArticle);
        Article getArticle = repository.getArticle(1L);
        checkWholeArticleFields(updatedArticle, getArticle);
    }

    @After
    public void deleteArticle() throws Exception {
        repository.deleteAll();
        checkArticleSize(0);
        Article deletedArticle = new Article(1L, ARAHANSA, SUBJECT, HELLO_WORLD);
        repository.save(deletedArticle);
        checkArticleSize(1);
        repository.delete(deletedArticle.getId());
        checkArticleSize(0);
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
