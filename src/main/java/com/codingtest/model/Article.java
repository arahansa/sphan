package com.codingtest.model;

import com.codingtest.validation.NotEmptyNotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by arahansa on 2015-06-28.
 */
@NotEmptyNotNull(
        subjectFieldName = "subject",
        nickFieldName = "nick"
)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String subject;

    private String nick;


    // 여기서부터 게시글 내용글 레이지로딩. 손권남님 위키 onetoone 부분 참고
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "ARTICLE_CONTENT_HOLDER",
            joinColumns = @JoinColumn(name = "ARTICLE_ID", foreignKey = @ForeignKey(name = "fk_id"), unique = true))
    @Column(name = "content")
    @Lob
    @JsonIgnore
    private List<String> contentHolder;



    public List<String> getContentHolder() {
        return contentHolder;
    }
    public void setContentHolder(List<String> contentHolder) {
        this.contentHolder = contentHolder;
    }
    public void setContent(String content) {
        if (getContentHolder() == null) {
            setContentHolder(new ArrayList<String>());
        }
        getContentHolder().clear();
        getContentHolder().add(content);
    }
    public String getContent() {
        if (getContentHolder() == null || getContentHolder().size() == 0) {
            return null;
        }
        return getContentHolder().get(0);
    }




    public Article() {}
    public Article(Long id) { this.id = id; }
    public Article(String nick, String subject, String content) {
        this.nick = nick;
        this.subject = subject;
        setContent(content);
    }
    public Article(Long id, String nick, String subject, String content) {
        this.id= id;
        this.nick = nick;
        this.subject = subject;
        setContent(content);
    }

    //Getter and Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getNick() { return nick; }
    public void setNick(String nick) {
        this.nick = nick;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", nick='" + nick + '\'' +
                ", content='" + getContent() + '\'' +
                '}';
    }
}
