package com.lynx.fqb.func.clause;

public interface Where<T> extends GroupBy<T> {

    default GroupBy<T> groupBy() {
        return em -> {
            return null;
        };
    }
}
