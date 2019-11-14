package com.jeffosmond.sqlsource;

import com.jeffosmond.sqlnode.SqlNode;

/**
 * @function   : 非动态节点对应的sqlSource(纯文本或者只有#{}的文本)
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 16:57
 */
public class RawSqlSource implements SqlSource {

    private SqlSource sqlSource;

    public RawSqlSource(SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(null);
        rootSqlNode.apply(context);
        // 在这里要先对sql节点进行解析
        SqlSourceParser sqlSourceParser = new SqlSourceParser();
        sqlSource = sqlSourceParser.parse(context.getSql());
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        // 从staticSqlSource中获取相应信息
        return sqlSource.getBoundSql(param);
    }
}
