package com.lynx.fqb.predicate;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.persistence.criteria.Predicate;

import org.junit.Test;
import org.mockito.Mock;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.path.Paths;

public class PredicateInterceptorsTest extends MockTestBase<Parent, Parent> {

    public PredicateInterceptorsTest() {
        super(Parent.class, Parent.class);
    }

    @Mock
    private PredicatesInterceptor<Parent> interceptor;

    @Test
    public void shouldInterceptPredicates() {
        Predicate[] predicates = new Predicate[] {};

        when(cq.where(predicates)).thenReturn(cq);
        when(interceptor.apply(any(), any(), any())).thenReturn(predicates);

        new Select.InterceptingSelect<>(interceptor)
                .from(Parent.class)
                .where(Predicates.of(Predicates.equal(Paths.get(Parent_.id), 1l)))
                .getResultList(em);

        verify(interceptor).apply(any(), any(), any());
    }

    @Test
    public void shouldAddPredicatesWithoutWhere() {
        Predicate[] predicates = new Predicate[] {};

        when(cq.where(predicates)).thenReturn(cq);
        when(interceptor.apply(any(), any(), any())).thenReturn(predicates);

        new Select.InterceptingSelect<>(interceptor)
                .from(Parent.class)
                .getResultList(em);

        verify(interceptor).apply(any(), any(), any());
    }

    @Test
    public void shouldAddPredicatesOnSingleResult() {
        Predicate[] predicates = new Predicate[] {};

        when(cq.where(predicates)).thenReturn(cq);
        when(interceptor.apply(any(), any(), any())).thenReturn(predicates);

        new Select.InterceptingSelect<>(interceptor)
                .from(Parent.class)
                .getSingleResult(em);

        verify(interceptor).apply(any(), any(), any());
    }

}
