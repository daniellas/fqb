package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;

import com.lynx.fqb.select.impl.JoinImpl;

public interface CustomSelection<S, R> extends Join<S, R> {

    /**
     * Add given joins to query
     * 
     * @param joins
     *            to apply
     * @return {@link Join} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    default Join<S, R> join(
            BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]> joins) {
        return JoinImpl.of(getSelectionCls(), getRootCls(), getSelections(), joins, getPredicatesInterceptor());
    }

}
