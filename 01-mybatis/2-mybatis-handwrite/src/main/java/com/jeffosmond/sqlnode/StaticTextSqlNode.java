package com.jeffosmond.sqlnode;

import com.jeffosmond.sqlsource.DynamicContext;

/**
 * @function   : 不带${ 只带#{ 的sqlNode
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 11:45
 */
public class StaticTextSqlNode implements SqlNode {

    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext context) {
        //sql文本追加
        context.appendSql(sqlText);
    }
}
