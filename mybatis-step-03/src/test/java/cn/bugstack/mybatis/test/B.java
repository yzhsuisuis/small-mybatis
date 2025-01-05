package cn.bugstack.mybatis.test;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 17:49
 */
public class B<T>{
    T e;
    T data;

    public B(){};

    private void setE(T t)
    {
        this.e = t;
    }
    //泛型方法
    private <T> T getE()
    {
        return (T) e;
    }
    public void setData(T data)
    {
        this.data = data;
    }
}