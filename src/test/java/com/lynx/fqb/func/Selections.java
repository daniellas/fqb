package com.lynx.fqb.func;

public interface Selections<RESULT, FROM> {

    default Sources<RESULT, FROM> entity(Class<RESULT> cls) {
        return null;
    }
}
