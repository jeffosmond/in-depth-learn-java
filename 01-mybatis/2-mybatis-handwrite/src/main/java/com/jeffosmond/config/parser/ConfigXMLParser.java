package com.jeffosmond.config.parser;

import com.jeffosmond.config.Configuration;
import com.jeffosmond.util.DocumentUtils;
import com.jeffosmond.util.ResourcesUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @function   : 全局配置XML文件解析类
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/27 10:28
 * @JeffNote   : 1、如果一个节点下的同名节点有多个，element.element()方法只会返回多个中的第一个，
 *                  element.elements会返回所有
 */
public class ConfigXMLParser {

    private Configuration configuration;

    public ConfigXMLParser() {
        configuration = new Configuration();
    }

    /**
     * 解析configuration节点
     * @param rootElement <configuration></configuration>
     * @return
     */
    public Configuration parseConfiguration(Element rootElement){
        //获取一级节点
        parseEnviroments(rootElement.element("environments"));
        parseMappers(rootElement.element("mappers"));
        return configuration;
    }

    /**
     * 解析mappers节点
     * @param mappersElement <mappers></mappers>
     */
    private void parseMappers(Element mappersElement) {
        List<Element> elements = mappersElement.elements("mapper");
        for (Element mapperElement : elements) {
            parseMapper(mapperElement);
        }
    }

    /**
     * 解析Mapper节点
     * @param mapperElement
     */
    private void parseMapper(Element mapperElement) {
        // 获取映射文件的路径
        String resource = mapperElement.attributeValue("resource");
        // 获取指定路径的IO流
        InputStream inputStream = ResourcesUtils.getResourcesAsStream(resource);
        // 获取映射文件对应的Document对象
        Document document = DocumentUtils.readDocument(inputStream);
        // 按照mapper标签语义去解析Document
        MapperXMLParser mapperParser = new MapperXMLParser(configuration);
        mapperParser.parseMapper(document.getRootElement());
    }

    /**
     * 解析environments节点
     * @param environments <environments></environments>
     */
    private void parseEnviroments(Element environments) {
        String defaultEnvId = environments.attributeValue("default");
        if(defaultEnvId == null || "".equals(defaultEnvId)){
            return;
        }
        List<Element> elements = environments.elements("environment");
        for (Element envElement : elements) {
            String envId = envElement.attributeValue("id");
            // 判断defaultEnvId和envId是否一致，一致再继续解析
            if (defaultEnvId.equals(envId)) {
                parseEnvironment(envElement);
            }
        }
    }

    /**
     * 解析environment节点
     * @param envElement <environment></environment>
     */
    private void parseEnvironment(Element envElement) {
        String type = envElement.attributeValue("type");
        Element dataSourceEnv = envElement.element("dataSource");
        type = type == null || "".equals(type) ? "DBCP" : type;
        if ("DBCP".equals(type)) {
            Properties properties = parseProperty(dataSourceEnv);
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            // 将解析出来的DataSource对象，封装到Configuration对象中
            configuration.setDataSource(dataSource);
        }
    }

    /**
     * 解析dataSource节点
     * @param dataSourceEnv
     * @return
     */
    private Properties parseProperty(Element dataSourceEnv) {
        Properties properties = new Properties();
        List<Element> elements = dataSourceEnv.elements("property");
        for (Element element : elements) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.put(name, value);
        }
        return properties;
    }
}
