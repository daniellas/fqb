package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesEndsWithITest extends IntegrationTestBase {

    private static final String PATTERN = ORDER_ONE_NUMBER.substring(ORDER_ONE_NUMBER.length() - 1,
            ORDER_ONE_NUMBER.length());

    @Test
    public void shouldRestrictByAttributeEndsWith() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(endsWith(SellOrder_.number, PATTERN))));
    }

    @Test
    public void shouldRestrictByPathEndsWith() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(endsWith(get(SellOrder_.number), PATTERN))));
    }

    @Test
    public void shouldRestrictByExpressionEndsWith() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(endsWith(
                                ofAttr(SellOrder_.number).andThen(upper()),
                                PATTERN))));
    }

}
