package com.thunisoft.service;

import com.thunisoft.domain.Comment;
import com.thunisoft.domain.News;
import com.thunisoft.domain.NewsUser;

import java.util.List;

public interface NewsService {
    List<News> findAll();



    News findOne(Integer newsId);

    List<Comment> findComments(Integer newsId);

    void saveComment(Integer newsId, String userName, String context);

    String regularNews(String userName, String newsTitle, String newsDetails, String img, String prc, String video);

    List<News> auditingNews();

    String AuditForApproval(String userName, Integer newsId, Integer status);

    String auditingUser(String userName, String passWord);

    List<News> myNews(String userName);

    void reload(String userName);

    String isCollect(String userName, Integer newsId);

    String isNotCollect(String userName, Integer newsId);

    NewsUser showCollect(String userName);

    News findById(Integer o);

    void saveNewsUser(String userName, Integer newsId);

    NewsUser showBrowse(String userName);

    String saveContent(String userName, String content);
}
