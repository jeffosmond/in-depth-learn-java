package com.jeffosmond.sqlnode;

import com.jeffosmond.sqlsource.DynamicContext;
import com.jeffosmond.util.OgnlUtils;

/**
 * @function   : if类型的sqlNode
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 17:15
 */
public class IfSqlNode implements SqlNode {

    /**
     * OGNL表达式
     */
    private String test;
    private SqlNode rootSqlNode;

    public IfSqlNode(String test, MixedSqlNode rootSqlNode) {
        this.test = test;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        boolean evaluateBoolean = OgnlUtils.evaluateBoolean(test, context.getBindings().get(DynamicContext.BINDING_PARAM));
        if (evaluateBoolean) {
            rootSqlNode.apply(context);
        }
    }
}
