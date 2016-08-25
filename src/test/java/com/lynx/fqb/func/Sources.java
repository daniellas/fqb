package com.lynx.fqb.func;

import java.util.Collections;

public interface Sources<RESULT, FROM> {

    default Results<RESULT> from(Class<FROM> fromCls) {
        return (cls) -> {
            return Collections.emptyList();
        };
    }
}
