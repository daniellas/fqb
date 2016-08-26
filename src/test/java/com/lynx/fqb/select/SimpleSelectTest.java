package com.lynx.fqb.select;

import org.junit.Test;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.paging.PageRequest;

public class SimpleSelectTest extends MockTestBase {

    @Test
    public void shouldCallResultList() {
        Select.using(em).from(Parent.class).list();

        Mockito.verify(em).getCriteriaBuilder();
        Mockito.verify(cb).createQuery(Parent.class);
        Mockito.verify(criteriaQuery).from(Parent.class);
        Mockito.verify(typedQuery).getResultList();
    }

    @Test
    public void pageableShouldApplyPaging() {
        Select.using(em).from(Parent.class).list(PageRequest.of(0, 1));

        Mockito.verify(em).getCriteriaBuilder();
        Mockito.verify(cb).createQuery(Parent.class);
        Mockito.verify(criteriaQuery).from(Parent.class);
        Mockito.verify(typedQuery).setFirstResult(0);
        Mockito.verify(typedQuery).setMaxResults(1);
        Mockito.verify(typedQuery).getResultList();
    }

    @Test
    public void shouldCallSingleResult() {
        Select.using(em).from(Parent.class).get();

        Mockito.verify(em).getCriteriaBuilder();
        Mockito.verify(cb).createQuery(Parent.class);
        Mockito.verify(criteriaQuery).from(Parent.class);
        Mockito.verify(typedQuery).getSingleResult();
    }

}
