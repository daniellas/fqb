package com.lynx.fqb.func.clause.impl;

import static com.lynx.fqb.func.QueryBuilder.*;

import java.util.function.BiFunction;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.func.clause.CustomSelection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class CustomSelectionImpl<S, R> implements CustomSelection<S, R> {

    private final Class<S> selectionCls;

    private final Class<R> rootCls;

    private final BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]> selections;

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
