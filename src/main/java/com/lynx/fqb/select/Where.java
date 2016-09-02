package com.lynx.fqb.select;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;
import com.lynx.fqb.select.ctx.QueryContext;

public class Where<F> implements QueryContext, CriteriaQueryApplier, WhereOperations<F> {

    private final QueryContext ctx;

    public Where(QueryContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public EntityManager getEntityManager() {
        return ctx.getEntityManager();
    }

    @Override
    public QueryContext getQueryContext() {
        return this;
    }

    @Override
    public List<F> apply(Pageable page) {
        return null;
    }

    @Override
    public F get() {
        return null;
    }

    @Override
    public <T> void apply(CriteriaQuery<T> criteriaQuery) {
        
    }

}
