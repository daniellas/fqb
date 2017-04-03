package com.lynx.fqb.func.clause;

public interface Having<T> extends OrderBy<T> {

    default OrderBy<T> orderBy() {
        return em -> {
            return null;
        };
    }

}
