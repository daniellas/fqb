package com.lynx.fqb.select;

import com.lynx.fqb.select.ctx.QueryContextSupplier;

public interface Restrictions<F> extends QueryContextSupplier {

    default WhereOperations<F> where() {
        return new Where<>(getQueryContext());
    }
}
