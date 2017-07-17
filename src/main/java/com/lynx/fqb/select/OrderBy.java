package com.lynx.fqb.select;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.intercept.PredicatesInterceptor;
import com.lynx.fqb.result.Result;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderBy<S, R> extends Result<S, R> {

    @Getter(AccessLevel.PROTECTED)
    private final boolean distinct;
    
    @Getter(AccessLevel.PROTECTED)
    private final Class<S> selectionCls;

    @Getter(AccessLevel.PROTECTED)
    private final Class<R> rootCls;

    @Getter(AccessLevel.PROTECTED)
    private final Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> selections;

    @Getter(AccessLevel.PROTECTED)
    private final Optional<BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]>> joins;

    @Getter(AccessLevel.PROTECTED)
    private final Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> restrictions;

    @Getter(AccessLevel.PROTECTED)
    private final Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]>> groupings;

    @Getter(AccessLevel.PROTECTED)
    private final Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> havings;

    private final BiFunction<CriteriaBuilder, Path<? extends R>, Order[]> orders;

    @Getter(AccessLevel.PROTECTED)
    private final PredicatesInterceptor<R> predicatesInterceptor;

    @Override
    protected Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Order[]>> getOrders() {
        return Optional.ofNullable(orders);
    }

}
