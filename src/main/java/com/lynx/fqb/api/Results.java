package com.lynx.fqb.api;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface Results<R> extends Function<Pageable, List<R>> {

    default List<R> list() {
        return apply(null);
    }

    default List<R> list(Pageable page) {
        return apply(page);
    }

}
