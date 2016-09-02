package com.lynx.fqb.select;

import com.lynx.fqb.select.ctx.QueryContextSupplier;

public interface Modifiers extends QueryContextSupplier {
    default Sources distinct() {
        return new Distinct(getQueryContext());
    }
}
