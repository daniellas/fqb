package com.lynx.fqb.util;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.predicate.PredicatesInterceptor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryBuilder {

    public static Function<EntityManager, CriteriaBuilder> getCriteriaBuilder() {
        return em -> em.getCriteriaBuilder();
    }

    public static <S> Function<CriteriaBuilder, CriteriaQuery<S>> createCriteriaQuery(Class<S> resultCls) {
        return cb -> cb.createQuery(resultCls);
    }

    public static <S, R> Function<CriteriaQuery<S>, QueryContext<S, R>> applyRoot(Class<R> rootCls) {
        return cq -> QueryContext.of(cq, cq.from(rootCls));
    }

    public static <S, R> Function<QueryContext<S, R>, QueryContext<S, R>> applySelection(CriteriaBuilder cb,
            Optional<BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]>> selection) {
        return ctx -> {
            return selection.map(s -> {
                return QueryContext.of(
                        ctx.getCq().select(cb.construct(ctx.getCq().getResultType(), s.apply(cb, ctx.getRoot()))),
                        ctx.getRoot());
            }).orElse(ctx);
        };
    }

    public static <S, R> Function<QueryContext<S, R>, QueryContext<S, R>> applyDistinct(CriteriaBuilder cb, Boolean distinct) {
        return ctx -> {
            return QueryContext.of(ctx.getCq().distinct(distinct), ctx.getRoot());
        };
    }

    public static <S, R> Function<QueryContext<S, R>, QueryContext<S, R>> applyRestriction(
            CriteriaBuilder cb,
            Optional<BiFunction<CriteriaBuilder, Root<R>, Predicate[]>> predicates,
            PredicatesInterceptor<R> interceptor) {
        return ctx -> {
            return predicates.map(p -> {
                interceptor.apply(cb, ctx.getRoot(), p.apply(cb, ctx.getRoot()));
                return QueryContext.of(ctx.getCq().where(p.apply(cb, ctx.getRoot())), ctx.getRoot());
            }).orElse(ctx);
        };
    }

    public static <S, R> Function<QueryContext<S, R>, QueryContext<S, R>> applyOrder(CriteriaBuilder cb,
            Optional<BiFunction<CriteriaBuilder, Root<R>, Order[]>> orders) {
        return ctx -> {
            return orders.map(o -> {
                return QueryContext.of(ctx.getCq().orderBy(o.apply(cb, ctx.getRoot())), ctx.getRoot());
            }).orElse(ctx);
        };
    }

    public static <S, R> Function<QueryContext<S, R>, TypedQuery<S>> createTypedQuery(EntityManager em) {
        return ctx -> em.createQuery(ctx.getCq());
    }

    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class QueryContext<S, R> {
        private final CriteriaQuery<S> cq;
        private final Root<R> root;
    }
}
