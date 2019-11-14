package com.jeffosmond.util;

import com.jeffosmond.sqlsource.DynamicContext;

/**
 * @author JeffOsmond
 * @date 2019/10/16
 */
public class BindingTokenParser implements TokenHandler {

    private DynamicContext context;

    public BindingTokenParser(DynamicContext context) {
        this.context = context;
    }

    @Override
    public String handleToken(String expression) {
        Object paramObject = context.getBindings().get("_parameter");
        if (paramObject == null) {
            // context.getBindings().put("value", null);
            return "";
        } else if (SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
            // context.getBindings().put("value", paramObject);
            return String.valueOf(paramObject);
        }

        // 使用Ognl api去获取相应的值
        Object value = OgnlUtils.getValue(expression, context.getBindings());
        String srtValue = value == null ? "" : String.valueOf(value);
        return srtValue;
    }
}
