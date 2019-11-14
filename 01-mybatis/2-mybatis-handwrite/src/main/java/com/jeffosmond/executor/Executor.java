package com.jeffosmond.executor;

import com.jeffosmond.config.Configuration;
import com.jeffosmond.config.MappedStatement;

import java.util.List;

/**
 * @author JeffOsmond
 * @date 2019/10/16
 */
public interface Executor {

    /**
     *
     * @param mappedStatement
     *            获取sql语句和入参出参等信息
     * @param configuration
     *            获取数据源对象
     * @param param
     *            入参对象
     * @return
     */
    <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param);
}
