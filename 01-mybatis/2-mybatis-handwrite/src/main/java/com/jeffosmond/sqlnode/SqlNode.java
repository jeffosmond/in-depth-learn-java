package com.jeffosmond.sqlnode;

import com.jeffosmond.sqlsource.DynamicContext;

public interface SqlNode {

    /**
     *
     * @param context
     */
    void apply(DynamicContext context);
}
