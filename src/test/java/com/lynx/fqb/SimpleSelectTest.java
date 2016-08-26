package com.lynx.fqb;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.lynx.fqb.entity.Parent;

public class SimpleSelectTest extends MockTestBase {

    @Mock
    private CriteriaQuery<Parent> criteriaQuery;

    @Mock
    private TypedQuery<Parent> typedQuery;

    @Before
    public void init() {
        super.init();
        Mockito.when(cb.createQuery(Parent.class)).thenReturn(criteriaQuery);
        Mockito.when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        Mockito.when(typedQuery.setFirstResult(Mockito.anyInt())).thenReturn(typedQuery);
        Mockito.when(typedQuery.setMaxResults(Mockito.anyInt())).thenReturn(typedQuery);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailOnNullEntitymanager() {
        Select.using(null).from(Parent.class).list();
    }

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

}
