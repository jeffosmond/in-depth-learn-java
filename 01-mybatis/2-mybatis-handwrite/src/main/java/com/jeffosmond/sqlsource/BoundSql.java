package com.jeffosmond.sqlsource;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @function   : 作用：封装已经完全解析成功的sql语句和解析出来的参数信息集合
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 10:57
 */
@Data
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void addParameterMapping(ParameterMapping parameterMapping) {
        this.parameterMappings.add(parameterMapping);
    }

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        super();
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }
}
