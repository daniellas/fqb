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
import com.lynx.fqb.order.Orders;

public class Having<S, R> extends OrderBy<S, R> {

    private final BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> havings;

    protected Having(
            boolean distinct,
            Class<S> selectionCls,
            Class<R> rootCls,
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> selections,
            Optional<BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]>> joins,
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> restrictions,
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]>> groupings,
            BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> havings,
            PredicatesInterceptor<R> predicatesInterceptor) {
        super(
                distinct,
                selectionCls,
                rootCls,
                selections,
                joins,
                restrictions,
                groupings,
                Optional.ofNullable(havings),
                null,
                predicatesInterceptor);
        this.havings = havings;
    }

    @Override
    protected Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> getHavings() {
        return Optional.ofNullable(havings);
    };

    /**
     * Apply given orders on query
     * 
     * @param orders
     *            returning function to apply
     * @return {@link OrderBy} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    public OrderBy<S, R> orderBy(BiFunction<CriteriaBuilder, Path<? extends R>, Order[]> orders) {
        return new OrderBy<>(
                isDistinct(),
                getSelectionCls(),
                getRootCls(),
                getSelections(),
                getJoins(),
                getRestrictions(),
                getGroupings(),
                Optional.ofNullable(havings),
                orders,
                getPredicatesInterceptor());
    }

    @SafeVarargs
    /**
     * Apply given orders on query
     * 
     * @param orders
     *            to apply
     * @return {@link OrderBy} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    public final OrderBy<S, R> orderBy(BiFunction<CriteriaBuilder, Path<? extends R>, Order>... orders) {
        return orderBy(Orders.of(orders));
    }

}
