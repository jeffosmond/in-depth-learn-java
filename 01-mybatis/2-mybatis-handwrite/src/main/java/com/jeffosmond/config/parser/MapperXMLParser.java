package com.jeffosmond.config.parser;

import com.jeffosmond.config.Configuration;
import org.dom4j.Element;

import java.util.List;

/**
 * @function   : Mapper配置XML文件解析类
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/27 14:20
 */
public class MapperXMLParser {

    private Configuration configuration;

    public MapperXMLParser(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析Mapper节点
     * @param mapperElement <mapper></mapper>
     */
    public void parseMapper(Element mapperElement) {
        String namespace = mapperElement.attributeValue("namespace");
        //此处可以使用XPath语法来进行通配
        List<Element> elements = mapperElement.elements("select");
        for (Element element : elements) {
            StatementXMLParser statementXMLParser =  new StatementXMLParser(configuration);
            statementXMLParser.parseStatement(element,namespace);
        }
    }
}
