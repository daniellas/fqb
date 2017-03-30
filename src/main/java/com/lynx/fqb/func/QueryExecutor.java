package com.lynx.fqb.func;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface QueryExecutor {

    public static Function<EntityManager, CriteriaBuilder> getCriteriaBuilder() {
        return em -> em.getCriteriaBuilder();
    }

    public static <T> Function<CriteriaBuilder, CriteriaQuery<T>> createCriteriaQuery(Class<T> resultCls) {
        return cb -> cb.createQuery(resultCls);
    }

    public static <S, R> Function<CriteriaQuery<S>, QueryContext<S, R>> applyRoot(Class<R> rootCls) {
        return cq -> QueryContext.of(cq, cq.from(rootCls));
    }

    public static <R> Function<QueryContext<R, R>, QueryContext<R, R>> applySelection() {
        return ctx -> QueryContext.of(ctx.getCq().select(ctx.getRoot()), ctx.getRoot());
    }

    public static <S, R> Function<QueryContext<S, R>, QueryContext<S, R>> applySelection(Selection<? extends S> selection) {
        return ctx -> QueryContext.of(ctx.getCq().select(selection), ctx.getRoot());
    }

    public static <S, R> Function<QueryContext<S, R>, TypedQuery<S>> createTypedQuery(EntityManager em) {
        return ctx -> em.createQuery(ctx.getCq());
    }

    @Getter
    @RequiredArgsConstructor(staticName = "of")
    static class QueryContext<S, R> {
        private final CriteriaQuery<S> cq;
        private final Root<R> root;
    }
}
