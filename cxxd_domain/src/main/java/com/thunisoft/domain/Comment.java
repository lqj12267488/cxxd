package com.thunisoft.domain;

public class Comment {
    private Integer id;
    private String userName;
    private Integer newsId;
    private String context;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comment() {

    }

    public Comment(Integer id, String userName, Integer newsId, String context) {

        this.id = id;
        this.userName = userName;
        this.newsId = newsId;
        this.context = context;
    }
}
