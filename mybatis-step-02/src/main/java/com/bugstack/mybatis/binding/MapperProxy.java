package com.bugstack.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 14:14
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String,String> sqlSession;

    private final Class<T> mappperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mappperInterface) {
        this.sqlSession = sqlSession;
        this.mappperInterface = mappperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里的method方法是queryName方法 ,在IUserDao里面定义了这个方法,所以
        System.out.println(method.getDeclaringClass());
        if(Object.class.equals(method.getDeclaringClass()))
        {
            return method.invoke(this,args);
        }
        else
        {
            String res = mappperInterface.getName() + "." + method.getName();
            return "你的被代理了！" + sqlSession.get(res);
        }

    }
}
