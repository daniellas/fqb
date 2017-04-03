package com.lynx.fqb.select;

public interface EntitySelection<S> extends Join<S, S> {

    default Join<S, S> join() {
        return null;
    }

}
