package com.codingtest.dto;

/**
 * Created by arahansa on 2015-06-29.
 */

public class Page {
    private Long id;

    private String subject;

    private String nick;


    public Page() {    }

    public Page(Long id, String subject, String nick) {
        this.id = id;
        this.subject = subject;
        this.nick = nick;
    }

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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
