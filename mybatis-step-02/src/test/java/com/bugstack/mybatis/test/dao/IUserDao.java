package com.bugstack.mybatis.test.dao;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 15:22
 */
public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);
}
