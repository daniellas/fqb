package com.lynx.fqb.select.impl;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
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
    private final Optional<BiFunction<CriteriaBuilder, Path<R>, Selection<?>[]>> selections;

    private final BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.Join<R, ?>[]> joins;

    @Getter
    private final PredicatesInterceptor<R> predicatesInterceptor;

    public Optional<BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.Join<R, ?>[]>> getJoins() {
        return Optional.of(joins);
    }
}
