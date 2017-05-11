package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.lynx.fqb.select.impl.HavingImpl;

public interface GroupBy<S, R> extends Having<S, R> {

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
