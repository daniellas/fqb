package com.lynx.fqb.select;

public interface Restrictions<F> extends QueryContextSupplier {
    
    default Where<F> where() {
        return new Where<>(getQueryContext());
    }
}
