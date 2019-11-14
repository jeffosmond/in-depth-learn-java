package com.jeffosmond.sqlsession;

import java.util.List;

/**
 * @author JeffOsmond
 * @date 2019/10/16
 */
public interface SqlSession {

    <T> T selectOne(String statementId, Object param);

    <T> List<T> selectList(String statementId, Object param);

}
