package com.jeffosmond.sqlsession;

import com.jeffosmond.config.Configuration;

/**
 * @author JeffOsmond
 * @date 2019/10/16
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }
}
