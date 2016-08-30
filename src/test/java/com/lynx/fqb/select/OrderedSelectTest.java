package com.lynx.fqb.select;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.paging.PageRequest;
import com.lynx.fqb.sort.SortApplier;

public class OrderedSelectTest extends MockTestBase {

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

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCallOrderByOnList() {
        Select.using(em).from(Parent.class).orderBy(sort).list();
        Mockito.verify(parentCriteriaQuery).orderBy(Mockito.anyList());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCallOrderByOnListPaged() {
        Select.using(em).from(Parent.class).orderBy(sort).list(PageRequest.of(0, 1));
        Mockito.verify(parentCriteriaQuery).orderBy(Mockito.anyList());
    }

}
