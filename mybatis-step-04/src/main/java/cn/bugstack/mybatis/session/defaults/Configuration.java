package cn.bugstack.mybatis.session.defaults;

import cn.bugstack.mybatis.binding.MapperRegistry;
import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 20:17
 */
public class Configuration {
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    private void addMappers(String packageName)
    {
        mapperRegistry.addMappers(packageName);
    }
    public <T> void addMapper(Class<T> type)
    {
        mapperRegistry.addMapper(type);
    }

    //总结归纳一下,一个xml文件所要经历的一切
    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }
    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        //通过映射注册器,影子
        return mapperRegistry.getMapper(type, sqlSession);
    }


}
