package com.lynx.fqb.func.clause;

public interface Join<T> extends Where<T> {

    default Where<T> where() {
        return em -> {
            return null;
        };
    }

}
