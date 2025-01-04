package com.bugstack;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2024-12-29 23:02
 */
public class UserServiceImpl implements UserService{
    @Override
    public String login(String username, String password) throws InterruptedException {
        if("yangbo".equals(username) && "123456".equals(password))
        {
            Thread.sleep(3000);
            return "用户登录成功了";

        }
        else
        {
            return "用户登录失败了";
        }

    }

    @Override
    public String register(String username, String password) throws InterruptedException {
        Thread.sleep(1000*3);
        return "用户登录成功了";
    }
}
