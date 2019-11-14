package com.jeffosmond.sqlsource;

/**
 * @function   : 一个完整的sql语句就是一个sqlSource
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 10:45
 */
public interface SqlSource {

    BoundSql getBoundSql(Object param);
}
