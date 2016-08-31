package com.lynx.fqb.select;

import javax.persistence.NonUniqueResultException;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.paging.PageRequest;

public class SimpleSelectITest extends IntegrationTestBase {

    @Test
    public void shouldReturnAll() {
        Assert.assertEquals(2, Select.using(em).from(Parent.class).list().size());
    }

    @Test
    public void firstShouldReturnOne() {
        Assert.assertEquals(1, Select.using(em).from(Parent.class).list(PageRequest.of(0, 1)).size());
    }

    @Test
    public void secondShouldReturnOne() {
        Assert.assertEquals(1, Select.using(em).from(Parent.class).list(PageRequest.of(1, 1)).size());
    }

    @Test(expected = NonUniqueResultException.class)
    public void singleShouldFailOnMoreThanOneElement() {
        Assert.assertNotNull(Select.using(em).from(Parent.class).get());
    }

    @Test
    public void shouldReturnAllOnFromSuplier() {
        Assert.assertEquals(2, Select.using(em).from(() -> Parent.class).list().size());
    }

}
