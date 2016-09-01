package com.lynx.fqb.select;

public interface Restrictions<F> extends QueryContextSupplier {

    default WhereOperations<F> where() {
        return new Where<>(getQueryContext());
    }
}
