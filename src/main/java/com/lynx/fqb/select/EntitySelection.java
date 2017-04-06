package com.lynx.fqb.select;

public interface EntitySelection<R> extends Join<R, R> {

    default Join<R, R> join() {
        return null;
    }

}
