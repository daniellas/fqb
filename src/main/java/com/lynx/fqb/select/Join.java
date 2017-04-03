package com.lynx.fqb.select;

import static com.lynx.fqb.util.QueryBuilder.*;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

public interface Join<S, R> extends Where<S, R> {

    Class<S> getSelectionCls();

    Class<R> getRootCls();

    Optional<BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]>> getSelections();

    default Where<S, R> where(BiFunction<CriteriaBuilder, Root<R>, Predicate[]> restrictions) {
        return em -> {
            return getSelections().map(s -> {
                return getCriteriaBuilder()
                        .andThen(createCriteriaQuery(getSelectionCls()))
                        .andThen(applyRoot(getRootCls()))
                        .andThen(applySelection(em.getCriteriaBuilder(), getSelections().get()))
                        .andThen(applyRestriction(em.getCriteriaBuilder(), restrictions))
                        .andThen(createTypedQuery(em))
                        .apply(em);
            }).orElseGet(() -> {
                return getCriteriaBuilder()
                        .andThen(createCriteriaQuery(getSelectionCls()))
                        .andThen(applyRoot(getRootCls()))
                        .andThen(applyRestriction(em.getCriteriaBuilder(), restrictions))
                        .andThen(createTypedQuery(em))
                        .apply(em);
            });
        };
    }

}
