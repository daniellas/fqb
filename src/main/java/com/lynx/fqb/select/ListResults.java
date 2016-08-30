package com.lynx.fqb.select;

import java.util.List;

import com.lynx.fqb.paging.Pageable;

@FunctionalInterface
public interface ListResults<R> {

    List<R> apply(Pageable page);

    default List<R> list() {
        return apply(null);
    }

    default List<R> list(Pageable page) {
        return apply(page);
    }

}
