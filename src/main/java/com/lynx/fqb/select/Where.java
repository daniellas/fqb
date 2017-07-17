package com.lynx.fqb.select;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.group.Groupings;
import com.lynx.fqb.intercept.PredicatesInterceptor;

public class Where<S, R> extends GroupBy<S, R> {

    private final BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> restrictions;

    protected Where(
            boolean distinct,
            Class<S> selectionCls,
            Class<R> rootCls,
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> selections,
            Optional<BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]>> joins,
            BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> restrictions,
            PredicatesInterceptor<R> predicatesInterceptor) {
        super(
                distinct,
                selectionCls,
                rootCls,
                selections,
                joins,
                Optional.ofNullable(restrictions),
                null,
                predicatesInterceptor);
        this.restrictions = restrictions;
    }

    /**
     * Apply given groupings to query
     * 
     * @param groupings
     *            returning function to apply
     * @return {@link GroupBy} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    public GroupBy<S, R> groupBy(BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]> groupings) {
        return new GroupBy<>(
                isDistinct(),
                getSelectionCls(),
                getRootCls(),
                getSelections(),
                getJoins(),
                Optional.ofNullable(restrictions),
                groupings,
                getPredicatesInterceptor());
    }

    @SafeVarargs
    /**
     * Apply given groupings to query
     * 
     * @param groupings
     *            to apply
     * @return {@link GroupBy} with allowed query methods
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    public final GroupBy<S, R> groupBy(BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>>... groupings) {
        return groupBy(Groupings.of(groupings));
    }

}
