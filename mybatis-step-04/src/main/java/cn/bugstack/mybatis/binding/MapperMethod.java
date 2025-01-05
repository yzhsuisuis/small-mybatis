package cn.bugstack.mybatis.binding;


import cn.bugstack.mybatis.session.SqlSession;
import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.mapping.SqlCommandType;
import cn.bugstack.mybatis.session.defaults.Configuration;

import java.lang.reflect.Method;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:映射器方法
 *@create 2025-01-05 11:41
 */
public class MapperMethod {
//    什么叫做映射器方法
    private final SqlCommand command;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        this.command = new SqlCommand(mapperInterface,method,configuration);
    }
    public Object execute(SqlSession sqlSession, Object[] args)
    {
        Object result = null;
        switch (command.getType())
        {
            case DELETE:
                break;
            case INSERT:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(), args);
                break;
            case UPDATE:
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());

        }
        return result;



    }

    //sql指令部分
    public static class SqlCommand
    {
        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Class<?> mapperInterface, Method method, Configuration configuration) {
            String statementName = mapperInterface.getName()+ "."+method.getName();
            MappedStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }
        public String getName() {
            return name;
        }
        public SqlCommandType getType() {
            return type;
        }
    }

}
