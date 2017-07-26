package com.lynx.fqb.path;

import static com.lynx.fqb.path.Paths.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.Item_;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.join.Joins;

public class PathsITest extends IntegrationTestBase {

    @Test
    public void shouldGetSinglePath() {
        assertNotNull(get(SellOrder_.id).apply(root(SellOrder.class)));
    }

    @Test
    public void shouldGetNestedPath() {
        assertNotNull(get(Item_.sellOrder).andThen(get(SellOrder_.number)).apply(root(Item.class)));
    }

    @Test
    public void shouldGetInheritedAttribute() {
        assertNotNull(get(SellOrder_.dateCreate).apply(root(SellOrder.class)));
    }

    @Test
    public void shouldGetInheritedNestedAttribute() {
        assertNotNull(get(Item_.sellOrder).andThen(get(SellOrder_.dateCreate)).apply(root(Item.class)));
    }

    @Test
    public void shouldGetFromJoin() {
        assertNotNull(Joins.inner(SellOrder_.items).andThen(Paths.get(Item_.id)).apply(cb, root(SellOrder.class)));
    }
}
