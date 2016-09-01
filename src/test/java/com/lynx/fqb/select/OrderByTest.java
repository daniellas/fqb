package com.lynx.fqb.select;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.sort.SortApplier;
import com.lynx.fqb.sort.Sorts;

public class OrderByTest extends QueryContextTestBase<Parent> {

    @Mock
    private Order order;

    @Mock
    SortApplier sort;

    @SuppressWarnings("unchecked")
    @Before
    public void init() {
        super.init();
        Mockito.when(parentCriteriaQuery.orderBy(Mockito.anyList())).thenReturn(parentCriteriaQuery);
        Mockito.when(sort.apply(Mockito.any(CriteriaBuilder.class), Mockito.any(Path.class))).thenReturn(order);
    }

    @Override
    protected Class<Parent> getFromCls() {
        return Parent.class;
    }

    @Override
    protected List<Parent> getListResults() {
        return Select.using(em).from(Parent.class).orderBy(Sorts.sorts(sort)).list();
    }

    @Override
    protected Parent getSingleResults() {
        return Select.using(em).from(Parent.class).orderBy(Sorts.sorts(sort)).get();
    }

    @Override
    protected QueryContext getQueryContext() {
        return (QueryContext) Select.using(em).from(Parent.class).orderBy(Sorts.sorts(sort));
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
    public void shouldCreateTypedQueryOnSupplier() {
        Select.using(em).from(Parent.class).orderBy(() -> Sorts.sorts(sort)).get();

        Mockito.verify(em).createQuery(parentCriteriaQuery);
    }


}
