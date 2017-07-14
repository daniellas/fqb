package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesGraterThanOrEqualToITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeGreaterThanOrEqualTo() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(greaterThanOrEqualTo(SellOrder_.number, ORDER_ONE_NUMBER.substring(1, 2)))));
    }

    @Test
    public void shouldRestrictByPathGreaterThanOrEqualTo() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(greaterThanOrEqualTo(get(SellOrder_.number), ORDER_ONE_NUMBER.substring(1, 2)))));
    }

    @Test
    public void shouldRestrictByExpressionGreaterThanOrEqualTo() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(greaterThanOrEqualTo(
                                ofAttr(SellOrder_.number).andThen(upper()),
                                ORDER_ONE_NUMBER.substring(1, 2)))));
    }

}
