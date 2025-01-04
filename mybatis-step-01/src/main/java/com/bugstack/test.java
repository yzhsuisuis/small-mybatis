package com.bugstack;

import java.lang.reflect.Proxy;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2024-12-29 23:17
 */
public class test {
    public static void main(String[] args) throws InterruptedException {
        UserService  userService = new UserServiceImpl();
        String res = userService.login("yangbo", "123456");
        System.out.println(res);

        UserService userServiceProxy = ProxyUtils.createProxy(new UserServiceImpl());
        res = userServiceProxy.login("yangbo", "123456");
        System.out.println(res);
    }
}
