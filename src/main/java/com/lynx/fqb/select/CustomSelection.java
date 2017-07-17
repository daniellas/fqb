package com.lynx.fqb.select;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.intercept.PredicatesInterceptor;

public class CustomSelection<S, R> extends Join<S, R> {

    private final Class<S> selectionCls;

    private final Class<R> rootCls;

    private final BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]> selections;

    protected CustomSelection(
            boolean distinct,
            Class<S> selectionCls,
            Class<R> rootCls,
            BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]> selections,
            PredicatesInterceptor<R> predicatesInterceptor) {
        super(
                distinct,
                selectionCls,
                rootCls,
                Optional.ofNullable(selections),
                null,
                predicatesInterceptor);
        this.selectionCls = selectionCls;
        this.rootCls = rootCls;
        this.selections = selections;
    }

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
    public Join<S, R> join(
            BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]> joins) {
        return new Join<>(
                isDistinct(),
                selectionCls,
                rootCls,
                Optional.of(selections),
                joins,
                getPredicatesInterceptor());
    }

}
