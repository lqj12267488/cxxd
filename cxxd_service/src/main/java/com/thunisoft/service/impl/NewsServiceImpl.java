package com.thunisoft.service.impl;

import com.thunisoft.dao.NewsDaoMapper;
import com.thunisoft.domain.Comment;
import com.thunisoft.domain.News;
import com.thunisoft.domain.NewsUser;
import com.thunisoft.service.NewsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDaoMapper newsDao;

    public List<News> findAll() {
        System.out.println("访问数据库");
        List<News> list = newsDao.findAll();
        return list;
    }



    public News findOne(Integer newsId) {
        return newsDao.findOne(newsId);
    }

    public List<Comment> findComments(Integer newsId) {
        return newsDao.findComments(newsId);
    }

    public void saveComment(Integer newsId, String userName, String context) {
         newsDao.saveComment(newsId,userName,context);
    }

    public String regularNews(String userName, String newsTitle, String newsDetails, String img, String prc, String video) {
        int max=200000;
        int min=0;
        Random random = new Random();
        int newsId = random.nextInt(max)%(max-min+1) + min;
        int collect = 0;
        int status=0;
        Date date = new Date();
        int num = 0;
        try {
            newsDao.regularNews(userName,newsId,newsTitle,newsDetails,collect,img,status,date,num,prc,video);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public List<News> auditingNews() {
        return newsDao.auditingNews();
    }

    public String AuditForApproval(String userName, Integer newsId, Integer status) {
        try {
            newsDao.AuditForApproval(userName,newsId,status);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String auditingUser(String userName, String passWord) {
        String userPassword = newsDao.auditingUser(userName);
        if(userPassword.equals(passWord)) {
            return "success";
        }else{
            return "fail";
        }
    }

    public List<News> myNews(String userName) {
        return newsDao.myNews(userName);
    }

    public void reload(String userName) {
        //先查询
            NewsUser user = newsDao.select(userName);
            if(user==null) {
                //第一次进入小程序 初始化用户信息
                newsDao.insert(userName);

            }
        }
//收藏
    public String isCollect(String userName, Integer newsId) {
        //先查询 newsUser表 将用户信息查询出来

        try {
            NewsUser user = newsDao.select(userName);
            String collect = user.getCollect();
            List<String> list = Arrays.asList(collect);
            ArrayList arrayList = new ArrayList();
            if(list.get(0)==null) {
                arrayList.add(newsId);

            }else{
                List<String> list1 = Arrays.asList(StringUtils.strip(collect, "[]"));
                for (String s : list1) {
                        String[] split = StringUtils.split(s, ", ");
                        for (String s1 : split) {

                            arrayList.add(Integer.parseInt(s1));
                        }

                }
                arrayList.add(newsId);
            }

            String str = arrayList.toString();
            newsDao.updateCollect(userName,str);
            return "success";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "fail";
        }


    }
//取消收藏
    public String isNotCollect(String userName, Integer newsId) {
        //先查询 newsUser表 将用户信息查询出来
        try {
            NewsUser user = newsDao.select(userName);
            String collect = user.getCollect();
            List<String> list = Arrays.asList(StringUtils.strip(collect, "[]"));
            ArrayList list1 = new ArrayList();
            for (String str : list) {
                String[] split = StringUtils.split(str, ", ");
                for (String s : split) {
                    list1.add(Integer.parseInt(s));
                }
            }
            list1.remove(newsId);
            //修改newsUser表
            String str = list1.toString();
            newsDao.updateCollect(userName,str);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

    }

    public NewsUser showCollect(String userName) {
        return newsDao.showCollect(userName);
    }

    public News findById(Integer o) {
        return newsDao.findOne(o);
    }

    public void saveNewsUser(String userName, Integer newsId) {
        NewsUser user = newsDao.select(userName);
        String collect = user.getBrowse();
        List<String> list = Arrays.asList(collect);
        ArrayList arrayList = new ArrayList();
        if(list.get(0)==null) {
            arrayList.add(newsId);
        }else{
            List<String> list1 = Arrays.asList(StringUtils.strip(collect, "[]"));
            for (String s : list1) {
                String[] split = StringUtils.split(s, ", ");
                for (String s1 : split) {

                    arrayList.add(Integer.parseInt(s1));
                }
            }
            arrayList.add(newsId);
        }
        String str = arrayList.toString();
        newsDao.updateBrowse(userName,str);
    }

    public NewsUser showBrowse(String userName) {
        return newsDao.showBrowse(userName);
    }

    public String saveContent(String userName, String content) {
        try {
            newsDao.saveContent(userName,content);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


}

