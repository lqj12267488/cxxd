import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.thunisoft.dao.NewsDaoMapper;
import com.thunisoft.domain.News;
import com.thunisoft.service.NewsService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.*;
import java.util.*;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class abc implements Runnable{
    @Autowired
    private NewsService newsService;

    @Autowired
  private  NewsDaoMapper newsDaoMapper;
    @Test
    public void aa() {
        List<News> list = newsService.findAll();
        for (News news : list) {
            System.out.println(news);
        }
    }


    @Test
    public void abc() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xiaochengxu", "root", "root");
        Statement statement = connection.createStatement();
        String sql = "select * from news";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
                           //获取stuname这列数据
            String userId = rs.getString("id");

                           System.out.println(userId);
                       }
                   rs.close();
                  connection.close();
    }


    @Test
    public void bb() {
        JSON parse = (JSON) JSON.parse("{'0':https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=203235963,4195753368&fm=26&gp=0.jpg}");
        System.out.println(parse);
    }
    @Test
    public void cc() {
      /*  HashMap<String, String> map = new HashMap<String, String>();
       map.put("0","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1117974880,2652983762&fm=26&gp=0.jpg");
       map.put("1","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1117974880,2652983762&fm=26&gp=0.jpg");
       map.put("2","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1117974880,2652983762&fm=26&gp=0.jpg");

        String string = JSON.toJSONString(map);
        System.out.println(string);*/
      /*  int max=200000;
        int min=0;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println(s);*/
      //["http://tmp/wxdba045815058b31c.o6zAJs_B4ht73NMCfottcK7hIJ-g.5Ky55KxybWJAea3b6b6891bbfaf0a4e05b299ac696bc.png"];
        /*ArrayList<String> list = new ArrayList<String>();
        list.add("[\"http://tmp/wxdba045815058b31c.o6zAJs_B4ht73NMCfottcK7hIJ-g.5Ky55KxybWJAea3b6b6891bbfaf0a4e05b299ac696bc.png\"]");
        String prc = list.get(0);
        String strip = StringUtils.strip(prc, "[]");
        System.out.println(strip);*/

       /* Date date = new Date();
        System.out.println(date);*/

       String userName = "123";

/*
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(23276);
        String string1 = list.toString();
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(23276);
        String string2 = list1.toString();*/
        //newsDaoMapper.insert(userName,string1,string2) ;

     /* String str =   newsDaoMapper.select(userName);
        List<String> list = Arrays.asList(StringUtils.split(str, ","));
        for (String s : list) {
            System.out.println(s);
            String strip = StringUtils.strip(s, "[]");
            System.out.println(strip);
            int anInt = Integer.parseInt(strip);
            System.out.println(anInt);
        }*/
     /*String str =null;
        //List<String> list1 = Arrays.asList(str);
        ArrayList<String> list = new ArrayList<String>();
        list.add("q");
        list.remove("q");
        System.out.println(list.toString());*/
        System.out.println(Integer.parseInt(" 234"));


    }

    public void run() {

    }
}
