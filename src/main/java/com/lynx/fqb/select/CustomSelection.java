package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;

import com.lynx.fqb.select.impl.JoinImpl;

public interface CustomSelection<S, R> extends Join<S, R> {

    default Join<S, R> join(BiFunction<CriteriaBuilder,From<R, R>, javax.persistence.criteria.Join<R, ?>[]> joins) {
        return JoinImpl.of(getSelectionCls(), getRootCls(), getSelections(), joins, getPredicatesInterceptor());
    }

}
