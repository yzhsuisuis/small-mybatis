package cn.bugstack.mybatis.builder;

import cn.bugstack.mybatis.session.defaults.Configuration;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 21:04
 */
public abstract class BaseBuilder {
    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}
