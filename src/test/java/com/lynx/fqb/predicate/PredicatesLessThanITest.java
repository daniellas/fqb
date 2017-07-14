package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesLessThanITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeLessThan() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(lessThan(SellOrder_.number, ORDER_ONE_NUMBER + "1"))));
    }

    @Test
    public void shouldRestrictByPathLessThan() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(lessThan(get(SellOrder_.number), ORDER_ONE_NUMBER + "1"))));
    }

    @Test
    public void shouldRestrictByExpressionLessThan() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(lessThan(
                                ofAttr(SellOrder_.number).andThen(upper()),
                                ORDER_ONE_NUMBER + "1"))));
    }

}
