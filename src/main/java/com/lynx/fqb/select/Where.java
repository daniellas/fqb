package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

import com.lynx.fqb.select.impl.GroupByImpl;

public interface Where<S, R> extends GroupBy<S, R> {

    /**
     * Apply given groupings to query
     * 
     * @param groupings
     *            to apply
     * @return {@link GroupBy} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    default GroupBy<S, R> groupBy(BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]> groupings) {
        return GroupByImpl.of(getSelectionCls(), getRootCls(), getSelections(), getJoins(), getRestrictions(),
                groupings, getPredicatesInterceptor());
    }
}
