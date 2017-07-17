package com.lynx.fqb.result;

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
public abstract class Result<S, R> implements Function<EntityManager, TypedQuery<S>> {

    /**
     * Get selection class
     * 
     * @return selection class
     */
    protected abstract Class<S> getSelectionCls();

    /**
     * Get root class
     * 
     * @return root class
     */
    protected abstract Class<R> getRootCls();

    /**
     * Check if select is distinct
     * 
     * @return true if distinct, false otherwise
     */
    protected abstract boolean isDistinct();

    protected abstract Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> getSelections();

    protected abstract Optional<BiFunction<CriteriaBuilder, From<R, R>, FetchParent<?, ?>[]>> getJoins();

    protected abstract Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> getRestrictions();

    protected abstract Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Order[]>> getOrders();

    protected abstract Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]>> getGroupings();

    protected abstract Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]>> getHavings();

    protected abstract PredicatesInterceptor<R> getPredicatesInterceptor();

    /**
     * Get all query results
     * 
     * @param em
     *            {@link EntityManager} to use for query execution
     * @return result list
     * @param <S>
     *            selection result type
     */
    public List<S> getResultList(EntityManager em) {
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
    public List<S> getResultList(EntityManager em, int offset, int limit) {
        TypedQuery<S> q = apply(em);

        q.setFirstResult(offset);
        q.setMaxResults(limit);

        return q.getResultList();
    }

    /**
     * Get single selection result, either success or error.
     * <p>
     * In case of {@link NoResultException} thrown,
     * {@link com.lynx.fqb.result.SingleResult.Result} is returned with null as
     * result value.
     * 
     * @param em
     *            {@link EntityManager} to use for query execution
     * @return either successful or error {@link SingleResult}
     * @param <S>
     *            selection result type
     */
    public SingleResult<S> getSingleResult(EntityManager em) {
        try {
            return SingleResult.ofResult(apply(em).getSingleResult());
        } catch (NoResultException e) {
            return SingleResult.ofResult(null);
        } catch (RuntimeException e) {
            return SingleResult.ofError(e);
        }
    }

    @Override
    public TypedQuery<S> apply(EntityManager em) {
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
