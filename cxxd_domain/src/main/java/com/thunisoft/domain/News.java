package com.thunisoft.domain;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    private Integer id;
    private String userId;
    private Integer newsId;
    private String newsTitle;
    private String newsDetails;
    private Integer collect;
    private String img;
    private Integer status;
    private String browse;
    private Date sendTime;
    private Integer num;
    private String prc;
    private String video;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDetails() {
        return newsDetails;
    }

    public void setNewsDetails(String newsDetails) {
        this.newsDetails = newsDetails;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBrowse() {
        return browse;
    }

    public void setBrowse(String browse) {
        this.browse = browse;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPrc() {
        return prc;
    }

    public void setPrc(String prc) {
        this.prc = prc;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public News() {

    }

    public News(Integer id, String userId, Integer newsId, String newsTitle, String newsDetails, Integer collect, String img, Integer status, String browse, Date sendTime, Integer num, String prc, String video) {

        this.id = id;
        this.userId = userId;
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsDetails = newsDetails;
        this.collect = collect;
        this.img = img;
        this.status = status;
        this.browse = browse;
        this.sendTime = sendTime;
        this.num = num;
        this.prc = prc;
        this.video = video;
    }
}
