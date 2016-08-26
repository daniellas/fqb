package com.lynx.fqb;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockTestBase {

    @Mock
    protected EntityManager em;

    @Mock
    protected CriteriaBuilder cb;

    @Before
    public void init() {
        Mockito.when(em.getCriteriaBuilder()).thenReturn(cb);
    }

}
