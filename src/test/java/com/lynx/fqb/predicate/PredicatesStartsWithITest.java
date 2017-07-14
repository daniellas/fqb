package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesStartsWithITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeStartsWith() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(startsWith(SellOrder_.number, ORDER_ONE_NUMBER.substring(0, 2)))));
    }

    @Test
    public void shouldRestrictByPathStartsWith() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(startsWith(get(SellOrder_.number), ORDER_ONE_NUMBER.substring(0, 2)))));
    }

    @Test
    public void shouldRestrictByExpressionStartsWith() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(startsWith(
                                ofAttr(SellOrder_.number).andThen(upper()),
                                ORDER_ONE_NUMBER.substring(0, 2)))));
    }

}
