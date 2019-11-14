package com.jeffosmond.config;

import com.jeffosmond.sqlsource.SqlSource;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @function   : Mapper.xml文件包装对象
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 10:42
 */
@Builder
@Getter
@Setter
@ToString
public class MappedStatement {

    private String statementId;
    private Class<?> parameterTypeClass;
    private Class<?> resultTypeClass;
    private String statementType;
    private SqlSource sqlSource;
}
