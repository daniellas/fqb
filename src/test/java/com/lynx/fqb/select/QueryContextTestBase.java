package com.lynx.fqb.select;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;

public abstract class QueryContextTestBase<T> extends MockTestBase {

    protected abstract Class<T> getFromCls();

    protected abstract List<T> getListResults();

    protected abstract T getSingleResults();

    @Test
    public void shouldCreateTypedQueryOnGet() {
        getSingleResults();

        Mockito.verify(em).createQuery(parentCriteriaQuery);
    }

    @Test
    public void shouldCreateTypedQueryOnList() {
        getListResults();

        Mockito.verify(em).createQuery(parentCriteriaQuery);
    }

    @Test
    public void shouldCallGetSingleResult() {
        getSingleResults();

        Mockito.verify(parentTypedQuery).getSingleResult();
    }

    @Test
    public void shouldCallGetListResult() {
        getListResults();

        Mockito.verify(parentTypedQuery).getResultList();
    }

}
