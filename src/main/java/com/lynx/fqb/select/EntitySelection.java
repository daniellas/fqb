package com.lynx.fqb.select;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.From;

import com.lynx.fqb.intercept.PredicatesInterceptor;
import com.lynx.fqb.join.Joins;

public class EntitySelection<R> extends Join<R, R> {

    private final boolean distinct;

    private final Class<R> rootCls;

    public EntitySelection(
            boolean distinct,
            Class<R> rootCls,
            PredicatesInterceptor<R> predicatesInterceptor) {
        super(
                distinct,
                rootCls,
                rootCls,
                Optional.empty(),
                null,
                predicatesInterceptor);
        this.distinct = distinct;
        this.rootCls = rootCls;
    }

    /**
     * Add given joins to query
     * 
     * @param joins
     *            returning function to apply
     * @return {@link Join} with allowed query methods
     * @param <R>
     *            selection root and result type
     */
    public Join<R, R> join(
            BiFunction<CriteriaBuilder, From<R, R>, javax.persistence.criteria.FetchParent<?, ?>[]> joins) {
        return new Join<>(
                distinct,
                rootCls,
                rootCls,
                Optional.empty(),
                joins,
                getPredicatesInterceptor());
    }

    @SafeVarargs
    /**
     * Add given joins to query
     * 
     * @param joins
     *            to apply
     * @return {@link Join} with allowed query methods
     * @param <R>
     *            selection root and result type
     */
    public final Join<R, R> join(BiFunction<CriteriaBuilder, From<R, R>, ? extends FetchParent<?, ?>>... joins) {
        return join(Joins.of(joins));
    }

}
