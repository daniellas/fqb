package com.lynx.fqb.select;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.intercept.PredicatesInterceptor;

public class GroupBy<S, R> extends Having<S, R> {

    private final BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]> groupings;

    protected GroupBy(
            Class<S> selectionCls,
            Class<R> rootCls,
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> selections,
            Optional<BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]>> joins,
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> restrictions,
            BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]> groupings,
            PredicatesInterceptor<R> predicatesInterceptor) {
        super(selectionCls,
                rootCls,
                selections,
                joins,
                restrictions,
                null,
                null,
                predicatesInterceptor);
        this.groupings = groupings;
    }

    /**
     * Add given having clauses to query
     * 
     * @param havings
     *            to apply
     * @return {@link Having} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    public Having<S, R> having(BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> havings) {
        return new Having<>(
                getSelectionCls(),
                getRootCls(),
                getSelections(),
                getJoins(),
                getRestrictions(),
                Optional.ofNullable(groupings),
                havings,
                getPredicatesInterceptor());
    }

}
