package com.lynx.fqb.order;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.Item_;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.path.Paths;

public class OrderITest extends IntegrationTestBase {

    @Test
    public void shouldCreateAscOrderFromPath() {
        Assert.assertNotNull(Orders.asc(Paths.get(SellOrder_.number)).apply(cb, root(SellOrder.class)));
    }

    @Test
    public void shouldCreateAscOrderFromNestedPath() {
        Assert.assertNotNull(Orders.asc(Paths.get(Item_.sellOrder).andThen(Paths.get(SellOrder_.number))).apply(cb, root(Item.class)));
    }

    @Test
    public void shouldCreateAscOrderFromSuperPath() {
        Assert.assertNotNull(Orders.asc(Paths.get(SellOrder_.dateCreate)).apply(cb, root(SellOrder.class)));
    }

    @Test
    public void shouldApplySingleOrder() {
        Assert.assertEquals(1, Orders.of(Orders.asc(Paths.get(SellOrder_.number))).apply(cb, root(SellOrder.class)).length);
    }


    @Test
    public void shouldApplyMultipleOrder() {
        Assert.assertEquals(2,
                Orders.of(
                        Orders.asc(Paths.get(SellOrder_.number)),
                        Orders.asc(Paths.get(SellOrder_.id)))
                        .apply(cb, root(SellOrder.class)).length);
    }

}
