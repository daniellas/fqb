package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.lynx.fqb.select.impl.HavingImpl;

public interface GroupBy<S, R> extends Having<S, R> {

    /**
     * Add given having clauses to query
     * 
     * @param havings
     *            to apply
     * @return {@link Having} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    default Having<S, R> having(BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> havings) {
        return HavingImpl.of(
                getSelectionCls(),
                getRootCls(),
                getSelections(),
                getJoins(),
                getRestrictions(),
                getGroupings(),
                havings,
                getPredicatesInterceptor());
    }

}
