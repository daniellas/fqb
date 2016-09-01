package com.lynx.fqb.select;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;

public abstract class QueryContextTestBase<T> extends MockTestBase {

    protected abstract QueryContext getQueryContext();

    protected abstract Class<T> getFromCls();

    protected abstract List<T> getListResults();

    protected abstract T getSingleResults();

    protected abstract CriteriaQuery<T> getCriteriaQuery();

    protected abstract TypedQuery<T> getTypedQuery();

    @Mock
    private Root<T> root;

    @SuppressWarnings("unchecked")
    @Before
    public void init() {
        super.init();

        Mockito.when(getCriteriaQuery().from(Mockito.any(Class.class))).thenReturn(root);
    }

    @Test
    public void shouldReturnFromCls() {
        Assert.assertEquals(getFromCls(), getQueryContext().getFromCls());
    }

    @Test
    public void shouldReturnEntityManager() {
        Assert.assertEquals(em, getQueryContext().getEntityManager());
    }

    @Test
    public void shouldCreateTypedQueryOnGet() {
        getSingleResults();

        Mockito.verify(em).createQuery(getCriteriaQuery());
    }

    @Test
    public void shouldCreateTypedQueryOnList() {
        getListResults();

        Mockito.verify(em).createQuery(getCriteriaQuery());
    }

    @Test
    public void shouldCallGetSingleResult() {
        getSingleResults();

        Mockito.verify(getTypedQuery()).getSingleResult();
    }

    @Test
    public void shouldCallGetListResult() {
        getListResults();

        Mockito.verify(getTypedQuery()).getResultList();
    }

    // FIXME This test is failing
    @Test
    @Ignore
    public void shouldReturnRoot() {
        getListResults();
        Assert.assertNotNull(getQueryContext().getRoot());
    }

}
