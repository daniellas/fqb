package com.lynx.fqb.func.clause.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lynx.fqb.func.clause.Selection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.lynx.fqb.func.QueryBuilder.*;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class SelectionImpl<S> implements Selection<S> {

    private final Class<S> selectionCls;

    @Override
    public TypedQuery<S> apply(EntityManager em) {
        return getCriteriaBuilder()
                .andThen(createCriteriaQuery(selectionCls))
                .andThen(applyRoot(selectionCls))
                .andThen(applyRootSelection())
                .andThen(createTypedQuery(em))
                .apply(em);
    }

}
