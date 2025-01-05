package cn.bugstack.mybatis.binding;


import cn.hutool.core.lang.ClassScanner;
import cn.bugstack.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 16:44
 */
public class MapperRegistry {
    //注册器,相当于给定一个class类 ,他就能直接返回他的代理类
    private static final Map<Class<?>,MapperProxyFactory<?>>  knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type , SqlSession sqlSession)
    {
    //    先根据这个type去找到对应的代理类
        MapperProxyFactory<?> mapperProxyFactory = knownMappers.get(type);
        if(mapperProxyFactory == null)
        {
            //如果本地没有注册的话就直接丢掉就完了
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        //工厂的责任就是用来创建实例的
        //上一个步骤这里用的是map
        try {
            // return mapperProxyFactory.newInstance(sqlSession);

            return (T)mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }
    public <T> void addMapper(Class<T> type)
    {
        //先判断是不是借口,如果是借口再继续
        if(type.isInterface())
        {
            if(hasMapper(type))
            {
                // 如果重复添加了，报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            knownMappers.put(type, new MapperProxyFactory<>(type));


        }

    }
    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public void addMappers(String packageName) {

        //hutool包里面的
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
//        这里是通过调用ClassScanner方法,来直接扫描这个包下的所有代码
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }






}
