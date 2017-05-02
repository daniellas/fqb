package com.lynx.fqb.select.impl;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.predicate.PredicatesInterceptor;
import com.lynx.fqb.select.Having;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class HavingImpl<S, R> implements Having<S, R> {
    
    @Override
    public Class<S> getSelectionCls() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<R> getRootCls() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]>> getSelections() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TypedQuery<S> apply(EntityManager t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PredicatesInterceptor<R> getPredicatesInterceptor() {
        // TODO Auto-generated method stub
        return null;
    }

}
