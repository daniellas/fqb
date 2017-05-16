package com.lynx.fqb.order;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.path.Paths;

public class OrderITest extends IntegrationTestBase {

    @Test
    public void shouldCreateAscOrderFromPath() {
        Assert.assertNotNull(Orders.asc(Paths.get(Parent_.name)).apply(cb, root(Parent.class)));
    }

    @Test
    public void shouldCreateAscOrderFromNestedPath() {
        Assert.assertNotNull(Orders.asc(Paths.get(Child_.parent).andThen(Paths.get(Parent_.name))).apply(cb, root(Child.class)));
    }

    @Test
    public void shouldCreateAscOrderFromSuperPath() {
        Assert.assertNotNull(Orders.asc(Paths.get(Parent_.dateCreate)).apply(cb, root(Parent.class)));
    }

    @Test
    public void shouldApplySingleOrder() {
        Assert.assertEquals(1, Orders.of(Orders.asc(Paths.get(Parent_.name))).apply(cb, root(Parent.class)).length);
    }

    @Test
    public void shouldApplyMultipleOrder() {
        Assert.assertEquals(2,
                Orders.of(
                        Orders.asc(Paths.get(Parent_.name)),
                        Orders.asc(Paths.get(Parent_.id)))
                        .apply(cb, root(Parent.class)).length);
    }

}
