package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;

import com.lynx.fqb.select.impl.JoinImpl;

public interface EntitySelection<R> extends Join<R, R> {

    /**
     * Add given joins to query
     * 
     * @param joins
     *            to apply
     * @return {@link Join} with allowed query methods
     * @param <R>
     *            selection root and result type
     */
    default Join<R, R> join(
            BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]> joins) {
        return JoinImpl.of(getSelectionCls(), getRootCls(), getSelections(), joins, getPredicatesInterceptor());
    }

}
