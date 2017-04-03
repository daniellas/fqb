package com.lynx.fqb.select;

import com.lynx.fqb.select.impl.HavingImpl;

public interface GroupBy<S, R> extends Having<S, R> {

    default Having<S, R> having() {
        return HavingImpl.of();
    }

}
