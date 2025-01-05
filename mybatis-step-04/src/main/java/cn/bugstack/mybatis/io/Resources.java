package cn.bugstack.mybatis.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:通过类加载器获取加载类的辅助器
 *@create 2025-01-04 20:24
 */
public class Resources {

    //这个方法是唯二对外暴露的方法
    public static Reader getResourceAsReader(String resources) throws IOException {
        return new InputStreamReader(getResourceAsStream(resources));

    }
    private static InputStream getResourceAsStream(String resource) throws IOException {
    //    这里的是比较骚气的,这里通过使用getClassLoaders 方法来进行
    //    通过兼容度来论述
    //    不同的运行环境可能使用不同的类加载器。例如，在 Web 应用中，资源文件可能由线程上下文类加载器加载，而在独立应用中，资源文件可能由系统类加载器加载。
        //
        // 通过同时使用两个类加载器，可以确保代码在各种环境中都能正确加载资源。
        /*
        *
        * */
        ClassLoader[] classLoaders = getClassLoaders();
        for (ClassLoader classLoader : classLoaders) {
            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if(null != inputStream)
            {
                return inputStream;
            }
        }
        throw new IOException("Could not find resource " + resource);
    }
    private static ClassLoader[] getClassLoaders()
    {
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()};


    }
    public static Class<?> classForName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

}
