package com.lynx.fqb;

import java.util.List;
import java.util.function.Function;

import com.lynx.fqb.paging.Pageable;

@FunctionalInterface
public interface ListResults<R> extends Function<Pageable, List<R>> {

    default List<R> list() {
        return apply(null);
    }

    default List<R> list(Pageable page) {
        return apply(page);
    }

}
