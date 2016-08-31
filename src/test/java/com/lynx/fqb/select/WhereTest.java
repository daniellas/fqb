package com.lynx.fqb.select;

import java.util.List;

import org.junit.Test;

import com.lynx.fqb.entity.Parent;

public class WhereTest extends QueryContextTestBase<Parent> {

    @Override
    protected QueryContext getQueryContext() {
        return Select.using(em).from(Parent.class).where();
    }

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
        return Select.using(em).from(Parent.class).get();
    }

    @Test
    public void shouldApplyRestrictions() {
        Select.using(em).from(Parent.class).where().list();
    }

}
