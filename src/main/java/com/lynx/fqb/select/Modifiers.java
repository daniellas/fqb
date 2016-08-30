package com.lynx.fqb.select;

public interface Modifiers extends SelectSupplier {
    default Distinct distinct() {
        return new Distinct(getSelect());
    }
}
