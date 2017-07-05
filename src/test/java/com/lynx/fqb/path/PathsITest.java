package com.lynx.fqb.path;

import static com.lynx.fqb.path.Paths.*;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.Item_;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PathsITest extends IntegrationTestBase {

    @Test
    public void shouldGetSinglePath() {
        Assert.assertNotNull(get(SellOrder_.id).apply(root(SellOrder.class)));
    }

    @Test
    public void shouldGetNestedPath() {
        Assert.assertNotNull(get(Item_.sellOrder).andThen(get(SellOrder_.number)).apply(root(Item.class)));
    }

    @Test
    public void shouldGetInheritedAttribute() {
        Assert.assertNotNull(get(SellOrder_.dateCreate).apply(root(SellOrder.class)));
    }

    @Test
    public void shouldGetInheritedNestedAttribute() {
        Assert.assertNotNull(get(Item_.sellOrder).andThen(get(SellOrder_.dateCreate)).apply(root(Item.class)));
    }

}
