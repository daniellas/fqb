package com.lynx.fqb.select;

public interface Where<S, R> extends GroupBy<S, R> {

    default GroupBy<S, R> groupBy() {
        return em -> {
            return null;
        };
    }
}
