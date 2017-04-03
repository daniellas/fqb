package com.lynx.fqb.func.clause;

public interface GroupBy<T> extends Having<T> {

    default Having<T> having() {
        return em -> {
            return null;
        };
    }

}
