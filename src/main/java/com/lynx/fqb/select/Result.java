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
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.intercept.PredicatesInterceptor;

/**
 * Select result
 * 
 * @author dalas0
 *
 * @param <S>
 *            selection type
 * @param <R>
 *            root type
 */
public interface Result<S, R> extends Function<EntityManager, TypedQuery<S>> {

    /**
     * Get selection class
     * 
     * @return selection class
     */
    Class<S> getSelectionCls();

    /**
     * Get root class
     * 
     * @return root class
     */
    Class<R> getRootCls();

    /**
     * Check if select is distinct
     * 
     * @return true if distinct, false otherwise
     */
    default boolean isDistinct() {
        return false;
    }

    default Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> getSelections() {
        return Optional.empty();
    }

    default Optional<BiFunction<CriteriaBuilder, From<R, R>, FetchParent<?, ?>[]>> getJoins() {
        return Optional.empty();
    }

    default Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> getRestrictions() {
        return Optional.empty();
    };

    default Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Order[]>> getOrders() {
        return Optional.empty();
    };

    default Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]>> getGroupings() {
        return Optional.empty();
    };

    default Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> getHavings() {
        return Optional.empty();
    }

    PredicatesInterceptor<R> getPredicatesInterceptor();

    /**
     * Get all query results
     * 
     * @param em
     *            {@link EntityManager} to use for query execution
     * @return result list
     * @param <S>
     *            selection result type
     */
    default List<S> getResultList(EntityManager em) {
        return apply(em).getResultList();
    }

    /**
     * Get paged query result
     * 
     * @param em
     *            {@link EntityManager} to use for query execution
     * @param offset
     *            to start result from
     * @param limit
     *            result to given number
     * @return result list
     * 
     * @param <S>
     *            selection result type
     * 
     */
    default List<S> getResultList(EntityManager em, int offset, int limit) {
        TypedQuery<S> q = apply(em);

        q.setFirstResult(offset);
        q.setMaxResults(limit);

        return q.getResultList();
    }

    /**
     * Get single selection result, either success or error.
     * <p>
     * In case of {@link NoResultException} thrown,
     * {@link com.lynx.fqb.select.SingleResult.Result} is returned with null as
     * result value.
     * 
     * @param em
     *            {@link EntityManager} to use for query execution
     * @return either successful or error {@link SingleResult}
     * @param <S>
     *            selection result type
     */
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
                .andThen(applyJoin(getJoins()))
                .andThen(applyDistinct(isDistinct()))
                .andThen(applyRestriction(getRestrictions(), getPredicatesInterceptor()))
                .andThen(applyGroup(getGroupings()))
                .andThen(applyHaving(getHavings()))
                .andThen(applyOrder(getOrders()))
                .andThen(createTypedQuery(em))
                .apply(em);
    }
}
