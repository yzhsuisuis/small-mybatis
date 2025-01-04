package com.bugstack;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2024-12-29 23:01
 */
public interface UserService {
    String login(String username ,String password) throws InterruptedException;

    String register(String username ,String password) throws InterruptedException;
}
