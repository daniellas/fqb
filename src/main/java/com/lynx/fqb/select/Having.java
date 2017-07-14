package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

import com.lynx.fqb.select.impl.OrderByImpl;

public interface Having<S, R> extends OrderBy<S, R> {

    /**
     * Apply given orders on query
     * 
     * @param orders
     *            to apply
     * @return {@link OrderBy} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    default OrderBy<S, R> orderBy(BiFunction<CriteriaBuilder, Path<? extends R>, Order[]> orders) {
        return OrderByImpl.of(
                getSelectionCls(),
                getRootCls(),
                getSelections(),
                getJoins(),
                getRestrictions(),
                getGroupings(),
                getHavings(),
                orders,
                getPredicatesInterceptor());
    }

}
