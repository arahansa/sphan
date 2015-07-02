package controller;

import com.codingtest.Application;
import com.codingtest.dto.Meta;
import com.codingtest.model.Article;
import com.codingtest.repository.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by arahansa on 2015-06-28.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ArticleControllerTest {


    public static final String ARTICLE = "/article";
    public static final String ARTICLE_1 ="/article/1";
    public static final String ARAHANSA = "arahansa";
    public static final String SUBJECT = "subject";
    public static final String HELLO_WORLD = "hello world";
    public static final String ARTICLE_100 = "/article/100";
    public static final String UPDATED_ARAHANSA = "updatedArahansa";

    private MediaType mediaType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("UTF-8"));

    @Autowired
    private WebApplicationContext ctx;
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("mock")
    private ArticleRepository repository;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        repository.save(new Article(1L, ARAHANSA, SUBJECT, HELLO_WORLD));
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(get(ARTICLE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.meta.codeStatus", is(Meta.SUCCESS_LIST)))
                .andExpect(jsonPath("$.data.[0].id", is(1)))
                .andExpect(jsonPath("$.data.[0].nick", is(ARAHANSA)))
                .andExpect(jsonPath("$.data.[0].subject", is(SUBJECT)));
    }

    @Test
    public void read() throws Exception{
        mockMvc.perform(get(ARTICLE_1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.nick", is(ARAHANSA)))
                .andExpect(jsonPath("$.data.subject", is(SUBJECT)));
    }

    @Test
    public void postTest() throws Exception {
        mockMvc.perform(post(ARTICLE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"id\": 2, \"nick\":\"%s\", \"subject\": \"%s\" }", ARAHANSA, SUBJECT)))
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.data.id", is(2)))
                .andExpect(jsonPath("$.data.nick", is(ARAHANSA)))
                .andExpect(jsonPath("$.data.subject", is(SUBJECT)));

        //post test without it field
        mockMvc.perform(post(ARTICLE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"nick\":\"%s\", \"subject\": \"%s\" }", ARAHANSA, SUBJECT)))
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.data.nick", is(ARAHANSA)))
                .andExpect(jsonPath("$.data.subject", is(SUBJECT)));
    }
    @Test
    public void update() throws Exception{
        mockMvc.perform(post(ARTICLE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"id\": 10, \"nick\":\"%s\", \"subject\": \"%s\" }", ARAHANSA, SUBJECT)))
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.data.id", is(10)))
                .andExpect(jsonPath("$.data.nick", is(ARAHANSA)))
                .andExpect(jsonPath("$.data.subject", is(SUBJECT)));

        mockMvc.perform(put("/article/10")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"id\": 10, \"nick\":\"%s\", \"subject\": \"%s\" }", UPDATED_ARAHANSA, SUBJECT)))
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.data.id", is(10)))
                .andExpect(jsonPath("$.data.nick", is(UPDATED_ARAHANSA)))
                .andExpect(jsonPath("$.data.subject", is(SUBJECT)));
    }

    @Test
    public void del() throws Exception{
        mockMvc.perform(delete(ARTICLE_1))
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.meta.codeStatus", is(Meta.SUCCESS_DEL)));

        mockMvc.perform(get(ARTICLE_1))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.data", nullValue()));
    }



    @Test
    public void emptyValueErrorHandling() throws Exception{
        mockMvc.perform(post(ARTICLE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"nick\":\"%s\"}", ARAHANSA)))
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.meta.codeStatus", is(Meta.ERROR_FIELDEMPTY)));
    }

    @Test
    public void notfoundHandling() throws Exception{
        mockMvc.perform(get(ARTICLE_100))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.meta.codeStatus", is(Meta.ERROR_NOTFOUND)));

        mockMvc.perform(delete(ARTICLE_100))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.meta.codeStatus", is(Meta.ERROR_NOTFOUND)));
    }

}
