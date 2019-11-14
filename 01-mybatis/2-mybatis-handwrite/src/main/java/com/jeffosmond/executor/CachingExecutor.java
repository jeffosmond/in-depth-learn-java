package com.jeffosmond.executor;

import com.jeffosmond.config.Configuration;
import com.jeffosmond.config.MappedStatement;

import java.util.List;

/**
 * @author JeffOsmond
 * @date 2019/10/16
 * 处理二级缓存
 */
public class CachingExecutor implements Executor {

    /**
     * 基本执行器
     */
    private Executor delegate;

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
        // 从二级缓存中根据sql语句获取处理结果（二级缓存如何存）
        // 如果有，则直接返回，如果没有则继续委托给基本执行器去处理
        return delegate.query(mappedStatement,configuration,param);
    }
}
