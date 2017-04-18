package com.lynx.fqb.select;

import static com.lynx.fqb.util.QueryBuilder.*;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.predicate.PredicatesInterceptor;

public interface Result<S, R> extends Function<EntityManager, TypedQuery<S>> {

    Class<S> getSelectionCls();

    Class<R> getRootCls();

    default boolean isDistinct() {
        return false;
    }

    default Optional<BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]>> getSelections() {
        return Optional.empty();
    }

    default Optional<BiFunction<CriteriaBuilder, Root<R>, Predicate[]>> getRestrictions() {
        return Optional.empty();
    };

    default Optional<BiFunction<CriteriaBuilder, Root<R>, Order[]>> getOrders() {
        return Optional.empty();
    };

    default Optional<BiFunction<CriteriaBuilder, Root<R>, Expression<?>[]>> getGroupings() {
        return Optional.empty();
    };

    PredicatesInterceptor<R> getPredicatesInterceptor();

    default List<S> getResultList(EntityManager em) {
        return apply(em).getResultList();
    }

    default List<S> getResultList(EntityManager em, int offset, int limit) {
        TypedQuery<S> q = apply(em);

        q.setFirstResult(offset);
        q.setMaxResults(limit);

        return q.getResultList();
    }

    default SingleResult<S> getSingleResult(EntityManager em) {
        try {
            return SingleResult.ofResult(apply(em).getSingleResult());
        } catch (NoResultException e) {
            return SingleResult.ofResult(null);
        } catch (RuntimeException e) {
            return SingleResult.ofError(e);
        }
    }

    @Override
    default TypedQuery<S> apply(EntityManager em) {
        return getCriteriaBuilder()
                .andThen(createCriteriaQuery(getSelectionCls(), getRootCls()))
                .andThen(applyRoot(getRootCls()))
                .andThen(applySelection(getSelections()))
                .andThen(applyDistinct(isDistinct()))
                .andThen(applyRestriction(getRestrictions(), getPredicatesInterceptor()))
                .andThen(applyGroup(getGroupings()))
                .andThen(applyOrder(getOrders()))
                .andThen(createTypedQuery(em))
                .apply(em);
    }
}
