package com.bugstack.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

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
    public T newInstance(Map<String,String> sqlSession)
    {
        MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }
}
