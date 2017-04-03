package com.lynx.fqb.func.clause;

public interface Selection<S> extends Join<S> {

    Class<S> getSelectionCls();

    default Join<S> join() {
        return em -> {
            return null;
        };
    }


}
