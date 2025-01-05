package cn.bugstack.mybatis.test;

import cn.bugstack.mybatis.test.dao.IUserDao;
import cn.bugstack.mybatis.binding.MapperRegistry;
import cn.bugstack.mybatis.session.SqlSession;
import cn.bugstack.mybatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 14:54
 */
public class ApiTest {
    private Logger logger = LoggerFactory.getLogger(ApiTest.class);
    @Test
    public void test_MapperProxyFactory() {
        //1.生成注册器,通过扫描某个包下面的的全部接口类,然后生成对应的代理类存放到一个map集合里面 Map<Class<?>,MapperProxyFactory<?>>
        MapperRegistry mapperRegistry = new MapperRegistry();
        //2.往这个注册器里面存入想要注册的类
        mapperRegistry.addMappers("com.bugstack.mybatis.test.dao");


    //    3.从sqlSession工厂里面获取到session,入参是mapper的注册器
        DefaultSqlSessionFactory factory = new DefaultSqlSessionFactory(mapperRegistry);

        //4. 返回一个proxyFactory.newInstance()方法
        SqlSession sqlSession = factory.openSession();
    //    获取映射器的对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String res = userDao.queryUserName("101");

        //执行成功了
        logger.info("测试结果：{}", res);


    }

    @Test
    public void test2()
    {
    //    再重新梳理一下那个图
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("com.bugstack.mybatis.test");

        DefaultSqlSessionFactory factory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = factory.openSession();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        String res = userDao.queryUserName("123455");
        logger.info("测试结果:{}",res);


    }
    @Test
    public void fanxing()
    {
        A<String> stringA = new A<>();
        stringA.setE("nihao");
        System.out.println((String) stringA.getE());


    }
    class A<T>{
        T e;
        public A(){};
        private void setE(T t)
        {
           this.e = t;
        }
        private <T> T getE()
        {
            return (T) e;
        }
    }

}
