package com.lynx.fqb.select;

public interface GroupBy<S,R> extends Having<S,R> {

    default Having<S,R> having() {
        return em -> {
            return null;
        };
    }

}
