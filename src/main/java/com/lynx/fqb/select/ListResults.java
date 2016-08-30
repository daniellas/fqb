package com.lynx.fqb.select;

import java.util.List;

import com.lynx.fqb.paging.Pageable;

@FunctionalInterface
public interface ListResults<R> {

    List<R> apply(Pageable page, boolean distinct);

    default List<R> list() {
        return apply(null, false);
    }

    default List<R> listDistinct() {
        return apply(null, true);
    }

    default List<R> list(Pageable page) {
        return apply(page, false);
    }

    default List<R> listDistinct(Pageable page) {
        return apply(page, true);
    }

}
