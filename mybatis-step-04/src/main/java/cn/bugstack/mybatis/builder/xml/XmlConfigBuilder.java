package cn.bugstack.mybatis.builder.xml;

import cn.bugstack.mybatis.builder.BaseBuilder;
import cn.bugstack.mybatis.io.Resources;
import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.mapping.SqlCommandType;
import cn.bugstack.mybatis.session.defaults.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *@auther:yangzihe @洋纸盒
 *@discription:
 *@create 2025-01-04 21:07
 */
public class XmlConfigBuilder extends BaseBuilder {

    private Element root;

    public XmlConfigBuilder(Reader reader) {
        super(new Configuration());
    //    通过调用dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {

            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 解析配置；类型别名、插件、对象工厂、对象包装工厂、设置、环境、类型转换、映射器
     *
     * @return Configuration
     */
    public Configuration parse()
    {
        try {
            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }

//       记住这里的Configuration是来自与父类的
        return configuration;
    }

    private void mapperElement(Element mappers) throws IOException, DocumentException, ClassNotFoundException {
        /*
        * 可能会纯在多个Mapper语句
        * 如果下图所示
        *    <mappers>
                <mapper resource="mapper/User_Mapper.xml"/>
                <mapper resource="mapper/Person_Mapper.xml"/>
             </mappers>
        *
        * */
        List<Element> mapperList = mappers.elements("mapper");
        for (Element element : mapperList) {
            String resource = element.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);

        //    通过xml文件流的读取方式获取文件
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();

            //注意这里的mid会 是namespace + .id 来实现相同的xml语句是不能够
            String namespace = root.attributeValue("namespace");
            List<Element> elementList = root.elements("select");
            for (Element node : elementList) {
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();
                HashMap<Integer, String> parameter = new HashMap<>();

                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                for(int i = 1;matcher.find();i++)
                {
                    //匹配 #{ id }

                    String g1 = matcher.group(1);

                    //匹配 id
                    String g2 = matcher.group(2);
                    parameter.put(i,g2);
                    sql  = sql.replace(g1, "?");
                }

                String msId = namespace + "." + id;
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, msId, sqlCommandType, parameterType, resultType, sql, parameter).build();
                // 添加解析 SQL
                configuration.addMappedStatement(mappedStatement);

            }


            //每次写的时候都不要被,敌人的来势汹汹所吓到
            // 注册Mapper映射器,addMapper,注册的是那个类的名,通过解析xml文件会得到
            configuration.addMapper(Resources.classForName(namespace));
        }

    }
}
