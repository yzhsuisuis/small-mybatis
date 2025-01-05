package cn.bugstack.mybatis.session.defaults;

import cn.bugstack.mybatis.session.SqlSession;
import cn.bugstack.mybatis.session.SqlSessionFactory;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 18:10
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    // private final MapperRegistry mapperRegistry;
    private final Configuration configuration;

    public DefaultSqlSessionFactory( Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
