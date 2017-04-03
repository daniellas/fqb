package com.lynx.fqb.select;

public interface Having<S,R> extends OrderBy<S,R> {

    default OrderBy<S,R> orderBy() {
        return em -> {
            return null;
        };
    }

}
