package com.lynx.fqb.select;

public interface CustomSelection<S, R> extends Join<S, R> {

    default Join<S, R> join() {
        return null;
    }

}
