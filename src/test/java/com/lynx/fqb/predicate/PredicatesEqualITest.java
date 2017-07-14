package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesEqualITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeEqual() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(equal(SellOrder_.id, ORDER_ONE_ID))));
    }

    @Test
    public void shouldRestrictByPathEqual() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(equal(get(SellOrder_.id), ORDER_ONE_ID))));
    }

    @Test
    public void shouldRestrictByExpressionEqual() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(equal(ofPath(get(SellOrder_.id)).andThen(sum(0l)), ORDER_ONE_ID))));
    }

    @Test
    public void shouldRestrictByAttributeNotEqual() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(notEqual(SellOrder_.id, ORDER_ONE_ID))));
    }

    @Test
    public void shouldRestrictByPathNotEqual() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(notEqual(get(SellOrder_.id), ORDER_ONE_ID))));
    }

    @Test
    public void shouldRestrictByExpressionNotEqual() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(notEqual(ofPath(get(SellOrder_.id)).andThen(sum(0l)), ORDER_ONE_ID))));
    }

}
