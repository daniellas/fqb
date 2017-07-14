package com.lynx.fqb.expression;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.selection.Selections.*;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class CountITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntityCount() {
        Assert.assertNotNull(Select.customFrom(Long.class, SellOrder.class).with(of(expr(count(SellOrder.class))))
                .getSingleResult(em));
    }

    @Test
    public void shouldSelectEntityDistinctCount() {
        Assert.assertNotNull(Select.customFrom(Long.class, SellOrder.class)
                .with(of(expr(countDistinct(SellOrder.class)))).getSingleResult(em));
    }

    @Test
    public void shouldSelectPathCount() {
        Assert.assertNotNull(Select.customFrom(Long.class, SellOrder.class)
                .with(of(expr(count(get(SellOrder_.number))))).getSingleResult(em));
    }

    @Test
    public void shouldSelectAttributeCount() {
        Assert.assertNotNull(Select.customFrom(Long.class, SellOrder.class)
                .with(of(expr(count(SellOrder_.number)))).getSingleResult(em));
    }

    @Test
    public void shouldSelectPathDistinctCount() {
        Assert.assertNotNull(Select.customFrom(Long.class, SellOrder.class)
                .with(of(expr(countDistinct(get(SellOrder_.number))))).getSingleResult(em));
    }

    @Test
    public void shouldSelectAttributeDistinctCount() {
        Assert.assertNotNull(Select.customFrom(Long.class, SellOrder.class)
                .with(of(expr(countDistinct(SellOrder_.number)))).getSingleResult(em));
    }

}
