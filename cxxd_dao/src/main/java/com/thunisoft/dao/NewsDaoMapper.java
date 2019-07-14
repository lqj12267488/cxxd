package com.thunisoft.dao;

import com.thunisoft.domain.Comment;
import com.thunisoft.domain.News;
import com.thunisoft.domain.NewsUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface NewsDaoMapper {

    @Select("select * from news where status=1 order by sendTime desc")
    List<News> findAll();

    @Select("select * from news where newsId=#{newId}")
    News findOne(Integer newsId);

    @Select("select * from comment where newsId=#{newsId}")
    List<Comment> findComments(Integer newsId);

    @Insert("insert into comment (userName,newsId,context) values(#{userName},#{newsId},#{context})")
    void saveComment(@Param("newsId")Integer newsId,@Param("userName") String userName, @Param("context")String context);

    @Insert("insert into news values(null,#{userName},#{newsId},#{newsTitle},#{newsDetails},#{collect},#{img},#{status},null,#{date},#{num},#{prc},#{video})")
    void regularNews(@Param("userName")String userName, @Param("newsId")int newsId, @Param("newsTitle")String newsTitle, @Param("newsDetails")String newsDetails, @Param("collect")int collect, @Param("img")String img, @Param("status")int status, @Param("date")Date date, @Param("num")int num, @Param("prc")String prc, @Param("video")String video);

    @Select("select * from news where status=0")
    List<News> auditingNews();

    @Update("update news set status=#{status} where newsId=#{newsId} and userName=#{userName}")
    void AuditForApproval(@Param("userName") String userName, @Param("newsId")Integer newsId, @Param("status")Integer status);

    @Select("select password from user where userName=#{userName}")
    String auditingUser(@Param("userName") String userName);

    @Insert("INSERT INTO newsUser VALUES (null,#{userName},null,null)")
    void insert(@Param("userName") String userName);

    @Select("select * from newsUser where userName=#{userName}")
    NewsUser select(String userName);

    @Select("select * from news where userName=#{userName}")
    List<News> myNews(String userName);

    @Update("update newsUser set collect =#{str} where userName =#{userName}")
    void updateCollect(@Param("userName") String userName, @Param("str")String str);

    @Select("select * from newsUser where userName=#{userName}")
    NewsUser showCollect(String userName);

    @Update("update newsUser set browse =#{str} where userName =#{userName}")
    void updateBrowse(@Param("userName") String userName, @Param("str")String str);

    @Select("select * from newsUser where userName=#{userName}")
    NewsUser showBrowse(String userName);

    @Insert("insert into opinion values(null,#{userName},#{content})")
    void saveContent(@Param("userName") String userName, @Param("content")String content);
}
