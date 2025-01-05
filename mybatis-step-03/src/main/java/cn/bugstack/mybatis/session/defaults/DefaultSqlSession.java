package cn.bugstack.mybatis.session.defaults;


import cn.bugstack.mybatis.binding.MapperRegistry;
import cn.bugstack.mybatis.session.SqlSession;


/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 17:03
 */
public class DefaultSqlSession implements SqlSession {
    private MapperRegistry mapperRegistry;



    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了!" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type,this);
    }
}
