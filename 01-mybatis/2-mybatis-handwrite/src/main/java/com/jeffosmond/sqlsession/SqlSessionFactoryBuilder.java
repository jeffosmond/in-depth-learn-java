package com.jeffosmond.sqlsession;

import com.jeffosmond.config.Configuration;
import com.jeffosmond.config.parser.ConfigXMLParser;
import com.jeffosmond.util.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * @author JeffOsmond
 * @date 2019/10/16
 * 此处使用构建者模式，隐藏构建细节(Configuration类的创建)
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream){
        Document document = DocumentUtils.readDocument(inputStream);
        ConfigXMLParser configXMLParser = new ConfigXMLParser();
        Configuration configuration = configXMLParser.parseConfiguration(document.getRootElement());
        return build(configuration);
    }

    private SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
