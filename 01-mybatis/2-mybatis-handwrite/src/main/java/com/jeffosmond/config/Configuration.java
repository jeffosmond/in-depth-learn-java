package com.jeffosmond.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @function   : Mybatis核心全局配置类
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/27 10:24
 */
@Getter
@Setter
@ToString
public class Configuration {

    /**
     * 数据源
     */
    private DataSource dataSource;

    /**
     * Mapper.xml文件解析后的对象集合
     */
    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    /**
     * 根据ID获取MappedStatement
     * @param statementId
     * @return
     */
    public MappedStatement getMappedStatementById(String statementId) {
        return mappedStatements.get(statementId);
    }

    /**
     * 添加一个MappedStatement
     * @param statementId
     * @param mappedStatement
     */
    public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
        mappedStatements.put(statementId, mappedStatement);
    }
}
