package com.jeffosmond.sqlsource;

import java.util.HashMap;
import java.util.Map;

/**
 * @function   : 动态上下文(作用：存储SqlNode解析过程中产生的sql片段，并完成字符串拼接 存储SqlNode解析过程中需要的入参信息)
 * @version    : v1.0
 * @author     : JeffOsmond
 * @createTime : 2019/9/30 11:26
 */
public class DynamicContext {

    /**
     * 绑定的全局方法入参
     */
    public static final String BINDING_PARAM = "_parameter";

    /**
     * 用于拼接sql的builder
     */
    private StringBuilder sb = new StringBuilder();

    private Map<String, Object> bindings = new HashMap<String, Object>();

    public DynamicContext(Object param) {
        bindings.put(BINDING_PARAM, param);
    }

    public void appendSql(String sql) {
        sb.append(sql);
        sb.append(" ");
    }

    public String getSql() {
        return sb.toString();
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }
}
