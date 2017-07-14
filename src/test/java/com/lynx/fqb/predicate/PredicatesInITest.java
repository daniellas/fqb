package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import java.util.Arrays;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesInITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeInArray() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(in(SellOrder_.id, ORDER_ONE_ID, ORDER_TWO_ID))));
    }

    @Test
    public void shouldRestrictByPathInArray() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(in(get(SellOrder_.id), ORDER_ONE_ID, ORDER_TWO_ID))));
    }

    @Test
    public void shouldRestrictByExpressionInArray() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(in(ofAttr(SellOrder_.id).andThen(sum(0l)), ORDER_ONE_ID, ORDER_TWO_ID))));
    }

    @Test
    public void shouldRestrictByAttributeInCollection() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(in(SellOrder_.id, Arrays.asList(ORDER_ONE_ID, ORDER_TWO_ID)))));
    }

    @Test
    public void shouldRestrictByPathInCollection() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(in(get(SellOrder_.id), Arrays.asList(ORDER_ONE_ID, ORDER_TWO_ID)))));
    }

    @Test
    public void shouldRestrictByExpressionInCollection() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(in(ofAttr(SellOrder_.id).andThen(sum(0l)),
                                Arrays.asList(ORDER_ONE_ID, ORDER_TWO_ID)))));
    }

    @Test
    public void shouldRestrictByAttributeNotInArray() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(notIn(SellOrder_.id, -1l, -2l))));
    }

    @Test
    public void shouldRestrictByPathNotInArray() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(notIn(get(SellOrder_.id), -1l, -2l))));
    }

    @Test
    public void shouldRestrictByExpressionNotInArray() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(notIn(ofAttr(SellOrder_.id).andThen(sum(0l)), -1l, -2l))));
    }

    @Test
    public void shouldRestrictByAttributeNotInCollection() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(notIn(SellOrder_.id, Arrays.asList(-1l, -2l)))));
    }

    @Test
    public void shouldRestrictByPathNotInCollection() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(notIn(get(SellOrder_.id), Arrays.asList(-1l, -2l)))));
    }

    @Test
    public void shouldRestrictByExpressionNotInCollection() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(notIn(ofAttr(SellOrder_.id).andThen(sum(0l)), Arrays.asList(-1l, -2l)))));
    }

}
