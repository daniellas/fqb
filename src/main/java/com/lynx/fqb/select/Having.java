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

public class Having<S, R> extends OrderBy<S, R> {

    private final BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> havings;

    protected Having(
            Class<S> selectionCls,
            Class<R> rootCls,
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> selections,
            Optional<BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]>> joins,
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> restrictions,
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]>> groupings,
            BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> havings,
            PredicatesInterceptor<R> predicatesInterceptor) {
        super(selectionCls,
                rootCls,
                selections,
                joins,
                restrictions,
                groupings,
                null,
                null,
                predicatesInterceptor);
        this.havings = havings;
    }

    @Override
    protected Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> getHavings() {
        return Optional.of(havings);
    };

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
    public OrderBy<S, R> orderBy(BiFunction<CriteriaBuilder, Path<? extends R>, Order[]> orders) {
        return new OrderBy<>(
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

}
