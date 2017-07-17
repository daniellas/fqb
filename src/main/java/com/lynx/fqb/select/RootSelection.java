package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.intercept.PredicatesInterceptor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RootSelection<S, R> {

    private final boolean distinct;

    private final Class<S> selectionCls;

    private final Class<R> rootCls;

    private final PredicatesInterceptor<R> predicatesInterceptor;

    /**
     * Choose selections to be returned by custom result query
     * 
     * @param selections
     *            to apply
     * @return {@link CustomSelection} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    public CustomSelection<S, R> with(BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]> selections) {
        return new CustomSelection<>(
                distinct,
                selectionCls,
                rootCls,
                selections,
                predicatesInterceptor);
    }

}
