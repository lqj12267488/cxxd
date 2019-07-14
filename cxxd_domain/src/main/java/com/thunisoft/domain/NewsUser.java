package com.thunisoft.domain;

public class NewsUser {
    private Integer id;
    private String userName;
    private String collect;
    private String browse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public String getBrowse() {
        return browse;
    }

    public void setBrowse(String browse) {
        this.browse = browse;
    }

    public NewsUser() {

    }

    public NewsUser(Integer id, String userName, String collect, String browse) {

        this.id = id;
        this.userName = userName;
        this.collect = collect;
        this.browse = browse;
    }
}
