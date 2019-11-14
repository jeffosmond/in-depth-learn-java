package com.jeffosmond.sqlsession;

/**
 * @author JeffOsmond
 * @date 2019/10/16
 */
public interface SqlSessionFactory {

    SqlSession openSqlSession();
}
