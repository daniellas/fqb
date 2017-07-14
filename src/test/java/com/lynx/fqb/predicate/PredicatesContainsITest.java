package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesContainsITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeContains() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(contains(SellOrder_.number, ORDER_ONE_NUMBER.substring(1, 2)))));
    }

    @Test
    public void shouldRestrictByPathContains() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(contains(get(SellOrder_.number), ORDER_ONE_NUMBER.substring(1, 2)))));
    }

    @Test
    public void shouldRestrictByExpressionContains() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(contains(
                                ofAttr(SellOrder_.number).andThen(upper()),
                                ORDER_ONE_NUMBER.substring(1, 2)))));
    }

}
