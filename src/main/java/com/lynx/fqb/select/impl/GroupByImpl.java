package com.lynx.fqb.select.impl;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.intercept.PredicatesInterceptor;
import com.lynx.fqb.select.GroupBy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class GroupByImpl<S, R> implements GroupBy<S, R> {

    @Getter
    private final Class<S> selectionCls;

    @Getter
    private final Class<R> rootCls;

    @Getter
    private final Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> selections;

    @Getter
    private final Optional<BiFunction<CriteriaBuilder,From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]>> joins;

    @Getter
    private final Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> restrictions;

    private final BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]> groupings;

    @Getter
    private final PredicatesInterceptor<R> predicatesInterceptor;

    @Override
    public Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]>> getGroupings() {
        return Optional.of(groupings);
    };

}
