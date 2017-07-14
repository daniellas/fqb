package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesLessThanOrEqualToITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeLessThanOrEqualTo() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(lessThanOrEqualTo(SellOrder_.number, ORDER_ONE_NUMBER + "1"))));
    }

    @Test
    public void shouldRestrictByPathLessThanOrEqualTo() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(lessThanOrEqualTo(get(SellOrder_.number), ORDER_ONE_NUMBER + "1"))));
    }

    @Test
    public void shouldRestrictByExpressionLessThanOrEqualTo() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(lessThanOrEqualTo(
                                ofAttr(SellOrder_.number).andThen(upper()),
                                ORDER_ONE_NUMBER + "1"))));
    }

}
