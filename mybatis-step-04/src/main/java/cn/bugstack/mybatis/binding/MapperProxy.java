package cn.bugstack.mybatis.binding;

import cn.bugstack.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 14:14
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 1L;

    private SqlSession sqlSession;

    private final Class<T> mapperInterface;

    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里的method方法是queryName方法 ,在IUserDao里面定义了这个方法,所以
        System.out.println(method.getDeclaringClass());
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            //在这里就开始直接执行了
            // return sqlSession.selectOne(method.getName(), args);
            final MapperMethod mapperMethod = cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }
    }
    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if(null==mapperMethod)
        {
            //找不到才去new
            mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;

    }
}
