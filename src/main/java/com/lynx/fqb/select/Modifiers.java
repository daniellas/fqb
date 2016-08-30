package com.lynx.fqb.select;

public interface Modifiers extends QueryContextSupplier {
    default Distinct distinct() {
        return new Distinct(getQueryContext());
    }
}
