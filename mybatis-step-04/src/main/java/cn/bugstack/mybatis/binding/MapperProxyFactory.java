package cn.bugstack.mybatis.binding;

import cn.bugstack.mybatis.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 14:15
 */
public class MapperProxyFactory<T>{
    private final Class<T> mapperInterface;

    private Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<Method, MapperMethod>();


    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession)
    {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface, methodCache);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }
}
