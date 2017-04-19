package com.lynx.fqb.select.impl;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.predicate.PredicatesInterceptor;
import com.lynx.fqb.select.Join;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class JoinImpl<S, R> implements Join<S, R> {

    @Getter
    private final Class<S> selectionCls;

    @Getter
    private final Class<R> rootCls;

    @Getter
    private final Optional<BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]>> selections;
    
    private final Function<From<R, R>, javax.persistence.criteria.Join<?, ?>[]> joins;
    

    @Getter
    private final PredicatesInterceptor<R> predicatesInterceptor;

    public Optional<Function<From<R, R>, javax.persistence.criteria.Join<?, ?>[]>> getJoins() {
        return Optional.of(joins);
    }
}
