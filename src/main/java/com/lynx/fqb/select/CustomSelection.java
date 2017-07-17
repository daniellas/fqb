package com.lynx.fqb.select;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;

import com.lynx.fqb.intercept.PredicatesInterceptor;

public class CustomSelection<S, R> extends Join<S, R> {

    private final Class<S> selectionCls;

    private final Class<R> rootCls;

    protected CustomSelection(
            Class<S> selectionCls,
            Class<R> rootCls,
            PredicatesInterceptor<R> predicatesInterceptor) {
        super(
                selectionCls,
                rootCls,
                Optional.empty(),
                null,
                predicatesInterceptor);
        this.selectionCls = selectionCls;
        this.rootCls = rootCls;
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
                selectionCls,
                rootCls,
                Optional.empty(),
                joins,
                getPredicatesInterceptor());
    }

}
