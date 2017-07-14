package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesGtITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeGt() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(gt(SellOrder_.id, 0l))));
    }

    @Test
    public void shouldRestrictByPathEndsWith() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(gt(get(SellOrder_.id), 0l))));
    }

    @Test
    public void shouldRestrictByExpressionEndsWith() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(gt(
                                ofAttr(SellOrder_.id).andThen(sum(0l)), 0l))));
    }

}
