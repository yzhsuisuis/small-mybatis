package cn.bugstack.mybatis.binding;

import cn.bugstack.mybatis.session.SqlSession;
import java.lang.reflect.Proxy;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 14:15
 */
public class MapperProxyFactory<T>{
    private final Class<T> mapperInterface;


    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession)
    {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }
}
