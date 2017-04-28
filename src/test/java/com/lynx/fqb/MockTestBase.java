package com.lynx.fqb;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RunWith(MockitoJUnitRunner.class)
public class MockTestBase<S, R> {

    private final Class<S> selectionCls;

    private final Class<R> rootCls;

    @Mock
    protected EntityManager em;

    @Mock
    protected CriteriaBuilder cb;

    @Mock
    protected CriteriaQuery<S> cq;

    @Mock
    protected Root<R> root;

    @Mock
    protected TypedQuery<S> typedQuery;

    @Before
    public void initBase() {
        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(selectionCls)).thenReturn(cq);
        when(cq.select(any())).thenReturn(cq);
        when(cq.from(rootCls)).thenReturn(root);
        when(cq.distinct(anyBoolean())).thenReturn(cq);
        when(em.createQuery(cq)).thenReturn(typedQuery);
    }
}
