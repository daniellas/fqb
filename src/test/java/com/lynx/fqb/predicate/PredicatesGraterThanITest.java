package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesGraterThanITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeGreaterThan() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(greaterThan(SellOrder_.number, ORDER_ONE_NUMBER.substring(1, 2)))));
    }

    @Test
    public void shouldRestrictByPathGreaterThan() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(greaterThan(get(SellOrder_.number), ORDER_ONE_NUMBER.substring(1, 2)))));
    }

    @Test
    public void shouldRestrictByExpressionGreaterThan() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(greaterThan(
                                ofAttr(SellOrder_.number).andThen(upper()),
                                ORDER_ONE_NUMBER.substring(1, 2)))));
    }

}
