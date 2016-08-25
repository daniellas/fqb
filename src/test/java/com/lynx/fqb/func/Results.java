package com.lynx.fqb.func;

import java.util.List;

@FunctionalInterface
public interface Results<RESULT> {

    List<RESULT> apply(Integer first, Integer max);

    default List<RESULT> list() {
        return apply(null, null);
    }

    default List<RESULT> list(Integer first, Integer max) {
        return apply(first, max);
    }

}
