package com.lynx.fqb.select;

import org.junit.Test;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.paging.PageRequest;

public class SimpleSelectTest extends MockTestBase {

    @Test
    public void shouldCallResultList() {
        Select.using(em).from(Parent.class).list();

        Mockito.verify(em).getCriteriaBuilder();
        Mockito.verify(cb).createQuery(Parent.class);
        Mockito.verify(parentCriteriaQuery).from(Parent.class);
        Mockito.verify(parentTypedQuery).getResultList();
    }

    @Test
    public void pageableShouldApplyPaging() {
        Select.using(em).from(Parent.class).list(PageRequest.of(0, 1));

        Mockito.verify(em).getCriteriaBuilder();
        Mockito.verify(cb).createQuery(Parent.class);
        Mockito.verify(parentCriteriaQuery).from(Parent.class);
        Mockito.verify(parentTypedQuery).setFirstResult(0);
        Mockito.verify(parentTypedQuery).setMaxResults(1);
        Mockito.verify(parentTypedQuery).getResultList();
    }

    @Test
    public void shouldCallSingleResult() {
        Select.using(em).from(Parent.class).get();

        Mockito.verify(em).getCriteriaBuilder();
        Mockito.verify(cb).createQuery(Parent.class);
        Mockito.verify(parentCriteriaQuery).from(Parent.class);
        Mockito.verify(parentTypedQuery).getSingleResult();
    }

}
