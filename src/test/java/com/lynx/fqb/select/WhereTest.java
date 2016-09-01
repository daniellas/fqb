package com.lynx.fqb.select;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.Test;

import com.lynx.fqb.entity.Parent;

public class WhereTest extends QueryContextTestBase<Parent> {

    @Override
    protected Class<Parent> getFromCls() {
        return Parent.class;
    }

    @Override
    protected List<Parent> getListResults() {
        return Select.using(em).from(Parent.class).where().list();
    }

    @Override
    protected Parent getSingleResults() {
        return Select.using(em).from(Parent.class).where().get();
    }

    @Override
    protected QueryContext getQueryContext() {
        return (QueryContext) Select.using(em).from(Parent.class).where();
    }

    @Override
    protected CriteriaQuery<Parent> getCriteriaQuery() {
        return parentCriteriaQuery;
    }

    @Override
    protected TypedQuery<Parent> getTypedQuery() {
        return parentTypedQuery;
    }

    @Test
    public void shouldApplyRestrictions() {
        Select.using(em).from(Parent.class).where().list();
    }

}
