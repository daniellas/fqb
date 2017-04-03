package com.lynx.fqb.select;

import com.lynx.fqb.select.impl.GroupByImpl;

public interface Where<S, R> extends GroupBy<S, R> {

    default GroupBy<S, R> groupBy() {
        return GroupByImpl.of();
    }
}
