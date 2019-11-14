package com.jeffosmond.config.parser;

import com.jeffosmond.config.Configuration;
import com.jeffosmond.config.MappedStatement;
import com.jeffosmond.sqlsource.SqlSource;
import org.dom4j.Element;

/**
 * @function   : MappedStatement解析(select、update、delete、insert标签)
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 10:30
 */
public class StatementXMLParser {

    private Configuration configuration;

    public StatementXMLParser(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析Mapper
     * @param mapperElement
     * @param namespace
     */
    public void parseStatement(Element mapperElement, String namespace) {
        //获取标签参数
        String id = mapperElement.attributeValue("id");
        String parameterType = mapperElement.attributeValue("parameterType");
        String resultType = mapperElement.attributeValue("resultType");
        String statementType = mapperElement.attributeValue("statementType");
        //转化指定参数类型
        Class<?> parameterTypeClass = resolveClass(parameterType);
        Class<?> resultTypeClass = resolveClass(resultType);
        statementType = statementType == null || "".equals(statementType) ? "prepared" : statementType;
        String statementId = namespace + "." + id;
        //转化Mapper标签Element为SqlSource
        SqlSource sqlSource = createSqlSource(mapperElement);
        //包装SqlSource为MappedStatement
        MappedStatement mappedStatement = MappedStatement.builder()
                .statementId(statementId)
                .statementType(statementType)
                .parameterTypeClass(parameterTypeClass)
                .resultTypeClass(resultTypeClass)
                .sqlSource(sqlSource)
                .build();
        //添加MappedStatement到configuration
        configuration.addMappedStatement(statementId,mappedStatement);
    }

    /**
     * 创建SqlSource
     * @param mapperElement
     * @return
     */
    private SqlSource createSqlSource(Element mapperElement) {
        ScriptXMLParser scriptParser = new ScriptXMLParser(configuration);
        SqlSource sqlSource = scriptParser.parseScriptNode(mapperElement);
        return sqlSource;
    }

    /**
     * 类转化
     * @param classPath
     * @return
     */
    private Class<?> resolveClass(String classPath) {
        try {
            return Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
