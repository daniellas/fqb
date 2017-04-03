package com.lynx.fqb.select.impl;

import static com.lynx.fqb.util.QueryBuilder.*;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.select.EntitySelection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class EntitySelectionImpl<S> implements EntitySelection<S> {

    private final Class<S> selectionCls;

    @Override
    public Optional<BiFunction<CriteriaBuilder, Root<S>, Selection<?>[]>> getSelections() {
        return Optional.empty();
    }

    @Override
    public Class<S> getRootCls() {
        return selectionCls;
    }

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
