package com.lynx.fqb.predicate;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.persistence.criteria.Predicate;

import org.junit.Test;
import org.mockito.Mock;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.intercept.PredicatesInterceptor;
import com.lynx.fqb.path.Paths;

public class PredicateInterceptorsTest extends MockTestBase<SellOrder, SellOrder> {

    public PredicateInterceptorsTest() {
        super(SellOrder.class, SellOrder.class);
    }

    @Mock
    private PredicatesInterceptor<SellOrder> interceptor;

    @Test
    public void shouldInterceptPredicates() {
        Predicate[] predicates = new Predicate[] {};

        when(cq.where(predicates)).thenReturn(cq);
        when(interceptor.apply(any(), any(), any())).thenReturn(predicates);

        new Select.InterceptingSelect<>(interceptor)
                .from(SellOrder.class)
                .where(Predicates.of(Predicates.equal(Paths.get(SellOrder_.id), 1l)))
                .getResultList(em);

        verify(interceptor).apply(any(), any(), any());
    }

    @Test
    public void shouldAddPredicatesWithoutWhere() {
        Predicate[] predicates = new Predicate[] {};

        when(cq.where(predicates)).thenReturn(cq);
        when(interceptor.apply(any(), any(), any())).thenReturn(predicates);

        new Select.InterceptingSelect<>(interceptor)
                .from(SellOrder.class)
                .getResultList(em);

        verify(interceptor).apply(any(), any(), any());
    }

    @Test
    public void shouldAddPredicatesOnSingleResult() {
        Predicate[] predicates = new Predicate[] {};

        when(cq.where(predicates)).thenReturn(cq);
        when(interceptor.apply(any(), any(), any())).thenReturn(predicates);

        new Select.InterceptingSelect<>(interceptor)
                .from(SellOrder.class)
                .getSingleResult(em);

        verify(interceptor).apply(any(), any(), any());
    }

}
