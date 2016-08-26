package com.lynx.fqb.select;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.Test;
import org.mockito.Mock;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Parent;

public class FromTest extends MockTestBase {

    @Mock
    private CriteriaQuery<Parent> criteriaQuery;

    @Mock
    private TypedQuery<Parent> typedQuery;

    @Test(expected = IllegalStateException.class)
    public void listShouldFailOnNullEntityManager() {
        Select.using(null).from(Parent.class).list();
    }

    @Test(expected = IllegalStateException.class)
    public void getShouldFailOnNullEntityManager() {
        Select.using(null).from(Parent.class).get();
    }

    @Test
    public void shouldSuccessOnEntityManagerProvided() {
        Select.using(em).from(Parent.class).list();
    }

    @Test
    public void shouldSuccessWithFromClassSpuuplier() {
        Select.using(em).from(() -> Parent.class).list();
    }

}
