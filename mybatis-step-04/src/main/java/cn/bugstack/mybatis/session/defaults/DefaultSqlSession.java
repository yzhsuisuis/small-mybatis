package cn.bugstack.mybatis.session.defaults;


import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.session.SqlSession;


/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 17:03
 */
public class DefaultSqlSession implements SqlSession {
    // private MapperRegistry mapperRegistry;
    //todo 这里我有一个点不太理解其他的地方都是带 final的 就这个地方不带final
    //注册器是丢在了这个肚子里面
    //注册器丢在了这个里面
    private Configuration configuration;
    public DefaultSqlSession(Configuration configuration) {
        this.configuration  = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了!" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了！" + "\n方法：" + statement + "\n入参：" + parameter + "\n待执行SQL：" + mappedStatement.getSql());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
