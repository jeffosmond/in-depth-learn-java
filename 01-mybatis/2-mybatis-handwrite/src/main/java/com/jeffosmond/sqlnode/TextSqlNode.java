package com.jeffosmond.sqlnode;

import com.jeffosmond.constant.DynamicTagEnum;
import com.jeffosmond.sqlsource.DynamicContext;
import com.jeffosmond.util.BindingTokenParser;
import com.jeffosmond.util.GenericTokenParser;

/**
 * @function   : 纯文本类型SqlNode
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 11:35
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    /**
     * 要处理${},直接替换成文本
     * @param context
     */
    @Override
    public void apply(DynamicContext context) {
        GenericTokenParser tokenParser
                = new GenericTokenParser("${","}",new BindingTokenParser(context));
        context.appendSql(tokenParser.parse(sqlText));

    }

    public boolean isDynamic() {
        if (sqlText.contains(DynamicTagEnum.NO_DEAL_TAG.getTag())) {
            return true;
        }
        return false;
    }


}
