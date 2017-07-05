package com.lynx.fqb.util;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.intercept.PredicatesInterceptor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryBuilder {

    public static Function<EntityManager, CriteriaBuilder> getCriteriaBuilder() {
        return em -> em.getCriteriaBuilder();
    }

    public static <S, R> Function<CriteriaBuilder, Context<S, R>> createCriteriaQuery(Class<S> resultCls, Class<R> rootCls) {
        return cb -> Context.of(cb, cb.createQuery(resultCls), null);
    }

    public static <S, R> Function<Context<S, R>, Context<S, R>> applyRoot(Class<R> rootCls) {
        return ctx -> Context.of(ctx.getCb(), ctx.getCq(), ctx.getCq().from(rootCls));
    }

    public static <S, R> Function<Context<S, R>, Context<S, R>> applySelection(
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> selection) {
        return ctx -> {
            return selection.map(s -> {
                return Context.of(
                        ctx.getCb(),
                        ctx.getCq().select(ctx.getCb().construct(ctx.getCq().getResultType(), s.apply(ctx.getCb(), ctx.getRoot()))),
                        ctx.getRoot());
            }).orElse(ctx);
        };
    }

    public static <S, R> Function<Context<S, R>, Context<S, R>> applyDistinct(boolean distinct) {
        return ctx -> Context.of(ctx.getCb(), ctx.getCq().distinct(distinct), ctx.getRoot());
    }

    public static <S, R> Function<Context<S, R>, Context<S, R>> applyJoin(Optional<BiFunction<CriteriaBuilder, From<R, R>, Join<?, ?>[]>> joins) {
        return ctx -> {
            joins.ifPresent(j -> {
                j.apply(ctx.getCb(), ctx.getRoot());
            });
            return ctx;
        };
    }

    public static <S, R> Function<Context<S, R>, Context<S, R>> applyRestriction(
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> predicates,
            PredicatesInterceptor<R> interceptor) {
        return ctx -> {
            return predicates.map(p -> {
                return Context.of(
                        ctx.getCb(),
                        ctx.getCq().where(
                                interceptor.apply(ctx.getCb(), ctx.getRoot(), p.apply(ctx.getCb(), ctx.getRoot()))),
                        ctx.getRoot());
            }).orElseGet(() -> {
                return Context.of(
                        ctx.getCb(),
                        ctx.getCq().where(
                                interceptor.apply(ctx.getCb(), ctx.getRoot(), new Predicate[] {})),
                        ctx.getRoot());
            });
        };
    }

    public static <S, R> Function<Context<S, R>, Context<S, R>> applyOrder(Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Order[]>> orders) {
        return ctx -> {
            return orders.map(o -> {
                return Context.of(ctx.getCb(), ctx.getCq().orderBy(o.apply(ctx.getCb(), ctx.getRoot())), ctx.getRoot());
            }).orElse(ctx);
        };
    }

    public static <S, R> Function<Context<S, R>, Context<S, R>> applyGroup(
            Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]>> groupings) {
        return ctx -> {
            return groupings.map(g -> {
                return Context.of(ctx.getCb(), ctx.getCq().groupBy(g.apply(ctx.getCb(), ctx.getRoot())), ctx.getRoot());
            }).orElse(ctx);
        };
    }

    public static <S, R> Function<Context<S, R>, Context<S, R>> applyHaving(Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> havings) {
        return ctx -> {
            return havings.map(h -> {
                return Context.of(ctx.getCb(), ctx.getCq().having(h.apply(ctx.getCb(), ctx.getRoot())), ctx.getRoot());
            }).orElse(ctx);
        };
    }

    public static <S, R> Function<Context<S, R>, TypedQuery<S>> createTypedQuery(EntityManager em) {
        return ctx -> em.createQuery(ctx.getCq());
    }

    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class Context<S, R> {
        private final CriteriaBuilder cb;
        private final CriteriaQuery<S> cq;
        private final Root<R> root;
    }
}
