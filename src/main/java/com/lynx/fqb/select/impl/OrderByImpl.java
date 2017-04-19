package com.lynx.fqb.select.impl;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.predicate.PredicatesInterceptor;
import com.lynx.fqb.select.OrderBy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class OrderByImpl<S, R> implements OrderBy<S, R> {

    @Getter
    private final Class<S> selectionCls;

    @Getter
    private final Class<R> rootCls;

    @Getter
    private final Optional<BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]>> selections;

    @Getter
    private final Optional<BiFunction<CriteriaBuilder, Root<R>, Predicate[]>> restrictions;

    @Getter
    private final Optional<BiFunction<CriteriaBuilder, Root<R>, Expression<?>[]>> groupings;

    private final BiFunction<CriteriaBuilder, Root<R>, Order[]> orders;

    @Getter
    private final PredicatesInterceptor<R> predicatesInterceptor;

    @Override
    public Optional<BiFunction<CriteriaBuilder, Root<R>, Order[]>> getOrders() {
        return Optional.ofNullable(orders);
    }

}
