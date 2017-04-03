package com.lynx.fqb.select.impl;

import static com.lynx.fqb.util.QueryBuilder.*;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.select.CustomSelection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class CustomSelectionImpl<S, R> implements CustomSelection<S, R> {

    @Getter
    private final Class<S> selectionCls;

    @Getter
    private final Class<R> rootCls;

    private final BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]> selections;

    @Override
    public Optional<BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]>> getSelections() {
        return Optional.of(selections);
    }

    @Override
    public TypedQuery<S> apply(EntityManager em) {
        return getCriteriaBuilder()
                .andThen(createCriteriaQuery(selectionCls))
                .andThen(applyRoot(rootCls))
                .andThen(applySelection(em.getCriteriaBuilder(), selections))
                .andThen(createTypedQuery(em))
                .apply(em);

    }

}
