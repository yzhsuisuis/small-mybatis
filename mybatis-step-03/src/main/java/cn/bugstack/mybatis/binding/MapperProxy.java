package cn.bugstack.mybatis.binding;

import cn.bugstack.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 14:14
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 1L;

    private SqlSession sqlSession;

    private final Class<T> mappperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mappperInterface) {
        this.sqlSession = sqlSession;
        this.mappperInterface = mappperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里的method方法是queryName方法 ,在IUserDao里面定义了这个方法,所以
        System.out.println(method.getDeclaringClass());
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return sqlSession.selectOne(method.getName(), args);
        }




    }
}
