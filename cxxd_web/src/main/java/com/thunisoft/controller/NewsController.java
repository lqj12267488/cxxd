package com.thunisoft.controller;


import com.alibaba.fastjson.JSON;
import com.thunisoft.domain.Comment;
import com.thunisoft.domain.News;
import com.thunisoft.domain.NewsUser;
import com.thunisoft.service.NewsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @RequestMapping("/findAll")
    public List<News> findAll() {
        return newsService.findAll();
    }

    //新闻详情
    @RequestMapping("/item")
    public News findOne(@RequestParam("newsId") Integer newsId,@RequestParam("userName")String userName) {
        //返回新闻详情
        News one = newsService.findOne(newsId);
        //保存用户浏览记录
        newsService.saveNewsUser(userName,newsId);
        return one;
    }

    //新闻评论刷新
    @RequestMapping("/comments")
    public List<Comment> findComments(Integer newsId) {
       return newsService.findComments(newsId);
    }

    //发表评论
    @RequestMapping("/saveComment")
    public Comment saveComment(@RequestParam("newsId") Integer newsId, @RequestParam("userName")String userName, @RequestParam("context")String context) {
         newsService.saveComment(newsId,userName,context);
        System.out.println(userName);
        Comment comment = new Comment();
        comment.setContext(context);
        comment.setNewsId(newsId);
        comment.setUserName(userName);
        return comment;
    }

    //发布新闻
    @RequestMapping("/regularNews")
    public String regularNews(@RequestParam("newsTitle")String newsTitle,@RequestParam("list")List<String> list,@RequestParam("video")String video,@RequestParam("newsDetails")String newsDetails,@RequestParam("userName")String userName) {
        System.out.println("访问...");
        HashMap<String, String> map = new HashMap<String, String>();
        if(list.size()==1) {
            //map.put("0",StringUtils.strip(list.get(0),"[]"));
            String str = list.get(0);
            //处理详情图片字符串
            String strip = manageStr(str);

            map.put("0",strip);
        }
        if (list.size()==2) {
            String str = list.get(0);
            String str1 = list.get(1);
            //处理详情图片字符串
            String manageStr = manageStr(str);
            String manageStr1 = manageStr(str1);

            map.put("0",manageStr);
            map.put("1",manageStr1);
        }
        if(list.size()==3) {
            String str = list.get(0);
            String str1 = list.get(1);
            String str2 = list.get(2);
            //处理详情图片字符串
            String manageStr = manageStr(str);
            String manageStr1 = manageStr(str1);
            String manageStr2 = manageStr(str2);

            map.put("0",manageStr);
            map.put("1",manageStr1);
            map.put("3",manageStr2);
        }
        //将图片map转成json字符串
        String img = JSON.toJSONString(map);
        //获取首页图片
        String prc = list.get(0);
        //处理首页图片
        String strip = StringUtils.strip(prc, "[]");
        String replace = strip.replace("\"", "");

        String str = newsService.regularNews(userName, newsTitle, newsDetails, img, replace, video);
        return str;
    }

    //处理字符串方法
    public String  manageStr(String str) {
        String replace = str.replace("\"", "");
        String strip = StringUtils.strip(replace, "[]");
        return strip;
    }

    //后台审核页面
    @RequestMapping("/auditingNews")
    public List<News> auditingNews() {
        return newsService.auditingNews();
    }

    //审核通过或者未通过
    @RequestMapping("/AuditForApproval")
    public String AuditForApproval(@RequestParam("userName")String userName,@RequestParam("newsId") Integer newsId,@RequestParam("status") Integer status) {
        String result = newsService.AuditForApproval(userName, newsId, status);
        return result;
    }

    //切换后台
    @RequestMapping("/auditingUser")
    public String auditingUser(@RequestParam("userName") String userName,@RequestParam("passWord")String passWord) {
        String str = newsService.auditingUser(userName, passWord);
        return str;
    }

    //我发布的新闻
    @RequestMapping("/myNews")
    public List<News> myNews(String userName) {
        List<News> list = newsService.myNews(userName);
        return list;
    }

    //进入小程序 保存用户信息
    @RequestMapping("/reload")
    public void reload(String userName) {
       newsService.reload(userName);
    }

    //收藏 取消收藏
    @RequestMapping("/isCollect")
    public String isCollect(@RequestParam("userName")String userName,@RequestParam("newsId")Integer newsId,@RequestParam("collect")Integer collect) {
        if(collect==1) {
            //收藏
            String result = newsService.isCollect(userName, newsId);
            return result;

        }
        if(collect==2) {
            //取消收藏
            String result = newsService.isNotCollect(userName, newsId);
            return result;
        }
        return null;
    }

    //查看我的收藏
    @RequestMapping("/showCollect")
    public List<News> showCollect(String userName) {
        NewsUser newsUser   =  newsService.showCollect(userName);
        String collect = newsUser.getCollect();
        List<String> list = Arrays.asList(StringUtils.strip(collect, "[]"));
        ArrayList list1 = new ArrayList();
        for (String str : list) {
            String[] split = StringUtils.split(str, ", ");
            for (String s : split) {
                list1.add(Integer.parseInt(s));
            }
        }
        ArrayList<News> newsArrayList = new ArrayList<News>();
        for (Object o : list1) {
          News news =   newsService.findById((Integer)o);
            newsArrayList.add(news);
        }
        return newsArrayList;
    }

    //观看记录
    @RequestMapping("/showBrowse")
    public List<News> showBrowse(String userName) {
        NewsUser newsUser = newsService.showBrowse(userName);
        String collect = newsUser.getBrowse();
        List<String> list = Arrays.asList(StringUtils.strip(collect, "[]"));
        ArrayList list1 = new ArrayList();
        for (String str : list) {
            String[] split = StringUtils.split(str, ", ");
            for (String s : split) {
                list1.add(Integer.parseInt(s));
            }
        }
        ArrayList<News> newsArrayList = new ArrayList<News>();
        for (Object o : list1) {
            News news =   newsService.findById((Integer)o);
            newsArrayList.add(news);
        }
        return newsArrayList;

    }

    //意见反馈
    @RequestMapping("/idea")
    public String idea(@RequestParam("userName")String userName,@RequestParam("content")String content){
        String result = newsService.saveContent(userName, content);
        return result;

    }

}
