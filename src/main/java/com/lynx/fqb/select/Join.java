package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.lynx.fqb.select.impl.WhereImpl;

public interface Join<S, R> extends Where<S, R> {

    /**
     * Apply given restrictions on query
     * 
     * @param restrictions
     *            to apply
     * @return {@link Where} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    default Where<S, R> where(BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> restrictions) {
        return WhereImpl.of(getSelectionCls(), getRootCls(), getSelections(), getJoins(), restrictions,
                getPredicatesInterceptor());
    }

}
