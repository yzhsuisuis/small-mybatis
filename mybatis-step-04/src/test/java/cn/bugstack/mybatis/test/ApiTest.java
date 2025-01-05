package cn.bugstack.mybatis.test;

import cn.bugstack.mybatis.io.Resources;
import cn.bugstack.mybatis.session.SqlSession;
import cn.bugstack.mybatis.session.SqlSessionFactory;
import cn.bugstack.mybatis.session.SqlSessionFactoryBuilder;
import cn.bugstack.mybatis.test.dao.IUserDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 14:54
 */
public class ApiTest {
    private Logger logger = LoggerFactory.getLogger(ApiTest.class);
    @Test
    public void test_SqlSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取映射器对象

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 3. 测试验证
        String res = userDao.queryUserInfoById("10001");
        String ans = userDao.queryUserHeadById("11234");
        logger.info("测试结果：{}", res);
        logger.info("测试结果2: {}",ans);


    }
    @Test
    public void test()
    {
        String sql ="This is a #{example} and another #{test} and #{boy}." ;
        Pattern pattern = Pattern.compile("(#\\{(.*?)})");
        Matcher matcher = pattern.matcher(sql);
        //这里相当于一个迭代器,他会寻找
        for(int i = 0;matcher.find();i++)
        {
            //这里的matcher是一个捕获组
            String g1 = matcher.group(1);
            String g2 = matcher.group(2);
            System.out.println(g1);
        }
        /*这里g1
        #{example}
        #{test}
        #{boy}
        * */
    }




}
