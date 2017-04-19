package com.lynx.fqb.select;

import java.util.function.Function;

import javax.persistence.criteria.From;

import com.lynx.fqb.select.impl.JoinImpl;

public interface CustomSelection<S, R> extends Join<S, R> {

    default Join<S, R> join(Function<From<R, R>, javax.persistence.criteria.Join<?, ?>[]> joins) {
        return JoinImpl.of(getSelectionCls(), getRootCls(), getSelections(), joins, getPredicatesInterceptor());
    }

}
