package com.lynx.fqb.select;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.sort.Sorts;

public class OrderByTest extends MockTestBase {

    @Test
    public void shouldReturnFromCls() {
        Assert.assertEquals(Parent.class, Select.using(em).from(Parent.class).orderBy(Sorts.sorts(Sorts.by(Parent_.id))).getFromCls());
    }

    @Test
    public void shouldReturnEntityManager() {
        Assert.assertEquals(em, Select.using(em).from(Parent.class).orderBy(Sorts.sorts(Sorts.by(Parent_.id))).getEntityManager());
    }

    @Test
    public void shouldCreateTypedQuery() {
        Select.using(em).from(Parent.class).orderBy(Sorts.sorts(Sorts.by(Parent_.id))).get();

        Mockito.verify(em).createQuery(parentCriteriaQuery);
    }

    @Test
    public void shouldCallGetSingleResult() {
        Select.using(em).from(Parent.class).orderBy(Sorts.sorts(Sorts.by(Parent_.id))).get();

        Mockito.verify(parentTypedQuery).getSingleResult();
    }

}
