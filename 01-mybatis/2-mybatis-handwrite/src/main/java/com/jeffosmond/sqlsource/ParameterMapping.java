package com.jeffosmond.sqlsource;

import lombok.Data;

/**
 * @function   :
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 10:59
 */
@Data
public class ParameterMapping {

    private String name;

    private Class<?> type;

    public ParameterMapping(String name) {
        this.name = name;
    }
}
