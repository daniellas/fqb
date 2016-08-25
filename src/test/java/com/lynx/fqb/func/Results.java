package com.lynx.fqb.func;

import java.util.Collection;

@FunctionalInterface
public interface Results<RESULT> {

    Collection<RESULT> apply(Class<RESULT> resultCls);

    default Collection<RESULT> list(Class<RESULT> cls) {
        return apply(cls);
    }
}
