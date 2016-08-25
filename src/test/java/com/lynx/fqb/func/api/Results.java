package com.lynx.fqb.func.api;

import java.util.List;

@FunctionalInterface
public interface Results<RESULT> {

    List<RESULT> apply(Integer first, Integer count);

    default List<RESULT> list() {
        return apply(null, null);
    }

    default List<RESULT> list(Integer first, Integer count) {
        return apply(first, count);
    }

}
