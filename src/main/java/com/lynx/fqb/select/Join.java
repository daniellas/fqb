package com.lynx.fqb.select;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.intercept.PredicatesInterceptor;

public class Join<S, R> extends Where<S, R> {

    private final BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]> joins;

    protected Join(
            Class<S> selectionCls,
            Class<R> rootCls,
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> selections,
            BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]> joins,
            PredicatesInterceptor<R> predicatesInterceptor) {
        super(selectionCls,
                rootCls,
                selections,
                Optional.empty(),
                null,
                predicatesInterceptor);
        this.joins = joins;
    }

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
    public Where<S, R> where(BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> restrictions) {
        return new Where<>(
                getSelectionCls(),
                getRootCls(),
                getSelections(),
                Optional.ofNullable(joins),
                restrictions,
                getPredicatesInterceptor());
    }

}
