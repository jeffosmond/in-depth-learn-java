package com.jeffosmond.sqlnode;

import com.jeffosmond.sqlsource.DynamicContext;

import java.util.List;

/**
 * @function   : 不同类型的SqlNode包装对象
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 11:27
 */
public class MixedSqlNode implements SqlNode{

    private List<SqlNode> sqlNodes;

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    /**
     * 调用所有实际的sqlNode对象的apply方法
     * @param context
     */
    @Override
    public void apply(DynamicContext context) {
        for (SqlNode sqlNode : sqlNodes) {
            sqlNode.apply(context);
        }
    }
}
