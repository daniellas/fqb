package com.lynx.fqb;

import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Parent;

@RunWith(MockitoJUnitRunner.class)
public class MockTestBase {

    @Mock
    protected EntityManager em;

    @Mock
    protected CriteriaBuilder cb;

    @Mock
    protected CriteriaQuery<Parent> parentCriteriaQuery;

    @Mock
    protected CriteriaQuery<Child> childCriteriaQuery;

    @Mock
    protected TypedQuery<Parent> parentTypedQuery;

    @Mock
    protected TypedQuery<Child> childTypedQuery;

    @Before
    public void init() {
        Mockito.when(em.getCriteriaBuilder()).thenReturn(cb);

        Mockito.when(cb.createQuery(Parent.class)).thenReturn(parentCriteriaQuery);
        Mockito.when(em.createQuery(parentCriteriaQuery)).thenReturn(parentTypedQuery);
        Mockito.when(parentTypedQuery.setFirstResult(Mockito.anyInt())).thenReturn(parentTypedQuery);
        Mockito.when(parentTypedQuery.setMaxResults(Mockito.anyInt())).thenReturn(parentTypedQuery);
        Mockito.when(parentTypedQuery.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(parentTypedQuery.getSingleResult()).thenReturn(new Parent());

        Mockito.when(cb.createQuery(Child.class)).thenReturn(childCriteriaQuery);
        Mockito.when(em.createQuery(childCriteriaQuery)).thenReturn(childTypedQuery);
        Mockito.when(childTypedQuery.setFirstResult(Mockito.anyInt())).thenReturn(childTypedQuery);
        Mockito.when(childTypedQuery.setMaxResults(Mockito.anyInt())).thenReturn(childTypedQuery);
        Mockito.when(childTypedQuery.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(childTypedQuery.getSingleResult()).thenReturn(new Child());

    }

}
