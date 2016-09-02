package com.lynx.fqb.select;

import com.lynx.fqb.select.ctx.SourceContextSupplier;

public interface Restrictions<R, F> extends SourceContextSupplier<F> {

    default WhereOperations<R, F> where() {
        return new Where<>(getSourceContext());
    }
}
