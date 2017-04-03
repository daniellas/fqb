package com.lynx.fqb.select;

public interface EntitySelection<S> extends Join<S, S> {

    Class<S> getSelectionCls();

    default Join<S, S> join() {
        return null;
    }

}
