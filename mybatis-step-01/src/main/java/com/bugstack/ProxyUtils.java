package com.bugstack;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2024-12-29 23:06
 */
public class ProxyUtils {
    public static UserService createProxy(UserService userService)
    {
          UserService userServiceProxy = (UserService) Proxy.newProxyInstance(ProxyUtils.class.getClassLoader(),
                new Class[]{UserService.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(method.getName().equals("login") || method.getName().equals("register"))
                        {
                            System.out.println(method.getName()+ "已经被代理了");
                            long start = System.currentTimeMillis();
                            Object  result = method.invoke(userService, args);
                            long end = System.currentTimeMillis();
                            System.out.println(method.getName()+"方法执行耗时" + (end - start)/1000+"s" );
                            return result;

                        }
                        else
                        {
                            Object  result = method.invoke(userService, args);
                            return result;

                        }
                    }
                });
          return userServiceProxy;


    }
}
