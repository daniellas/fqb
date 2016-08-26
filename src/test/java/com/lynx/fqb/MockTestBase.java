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

import com.lynx.fqb.entity.Parent;

@RunWith(MockitoJUnitRunner.class)
public class MockTestBase {

    @Mock
    protected EntityManager em;

    @Mock
    protected CriteriaBuilder cb;

    @Mock
    protected CriteriaQuery<Parent> criteriaQuery;

    @Mock
    protected TypedQuery<Parent> typedQuery;

    @Before
    public void init() {
        Mockito.when(em.getCriteriaBuilder()).thenReturn(cb);
        Mockito.when(cb.createQuery(Parent.class)).thenReturn(criteriaQuery);
        Mockito.when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        Mockito.when(typedQuery.setFirstResult(Mockito.anyInt())).thenReturn(typedQuery);
        Mockito.when(typedQuery.setMaxResults(Mockito.anyInt())).thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(Collections.emptyList());
        Mockito.when(typedQuery.getSingleResult()).thenReturn(new Parent());
    }

}
