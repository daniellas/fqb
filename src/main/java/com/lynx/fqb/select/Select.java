package com.lynx.fqb.select;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

public class Select implements QueryContext, SelectOperations {

    final EntityManager em;

    private Select(EntityManager em) {
        this.em = em;
    }

    public static SelectOperations using(EntityManager em) {
        return new Select(Optional.ofNullable(em).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public QueryContext getQueryContext() {
        return this;
    }

    @Override
    public <T> Optional<CriteriaQuery<T>> doApply(Class<T> fromCls) {
        return Optional.of(getCriteriaBuilder().createQuery(fromCls));
    }

    @Override
    public <T> Class<T> getFromCls() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Path<T> getRoot() {
        throw new UnsupportedOperationException();
    }

}
