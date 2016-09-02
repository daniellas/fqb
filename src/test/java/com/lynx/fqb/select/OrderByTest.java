package com.lynx.fqb.select;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.sort.SortProvider;
import com.lynx.fqb.sort.Sorts;

public class OrderByTest extends MockTestBase {

    @Mock
    private Order order;

    @Mock
    SortProvider sort;

    @SuppressWarnings("unchecked")
    @Before
    public void init() {
        super.init();
        Mockito.when(parentCriteriaQuery.orderBy(Mockito.anyList())).thenReturn(parentCriteriaQuery);
        Mockito.when(sort.apply(Mockito.any(CriteriaBuilder.class), Mockito.any(Root.class))).thenReturn(order);
    }

    @Test
    public void shouldCreateTypedQueryOnSupplier() {
        Select.using(em).from(Parent.class).orderBy(() -> Sorts.sorts(sort)).get();

        Mockito.verify(em).createQuery(parentCriteriaQuery);
    }

}
