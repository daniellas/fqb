package com.lynx.fqb.select;

public interface Modifiers extends QueryContextSupplier {
    default Sources distinct() {
        return new Distinct(getQueryContext());
    }
}
