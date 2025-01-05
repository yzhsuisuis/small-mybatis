package cn.bugstack.mybatis.session;

import cn.bugstack.mybatis.builder.xml.XmlConfigBuilder;
import cn.bugstack.mybatis.session.defaults.DefaultSqlSessionFactory;
import cn.bugstack.mybatis.session.defaults.Configuration;

import java.io.Reader;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 20:44
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(Reader reader)
    {
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }
    public SqlSessionFactory build(Configuration configuration)
    {
        return new DefaultSqlSessionFactory(configuration);
    }
}
