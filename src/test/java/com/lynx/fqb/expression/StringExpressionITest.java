package com.lynx.fqb.expression;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.selection.Selections.*;
import static org.junit.Assert.*;

import javax.persistence.Tuple;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.predicate.Predicates;

public class StringExpressionITest extends IntegrationTestBase {

    @Test
    public void shouldSelectStringLength() {
        Tuple result = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.number).andThen(length()))))
                .where(Predicates.of(Predicates.equal(SellOrder_.number, ORDER_ONE_NUMBER)))
                .getSingleResult(em)
                .getResult();

        assertEquals(ORDER_ONE_NUMBER.length(), result.get(0, Integer.class).intValue());
    }

    @Test
    public void shouldSelectUpperString() {
        Tuple result = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.number).andThen(concat("a")).andThen(upper()))))
                .where(Predicates.of(Predicates.equal(SellOrder_.number, ORDER_ONE_NUMBER)))
                .getSingleResult(em)
                .getResult();

        assertEquals(ORDER_ONE_NUMBER + "A", result.get(0, String.class));
    }

    @Test
    public void shouldSelectLowerString() {
        Tuple result = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.number).andThen(concat("A")).andThen(lower()))))
                .where(Predicates.of(Predicates.equal(SellOrder_.number, ORDER_ONE_NUMBER)))
                .getSingleResult(em)
                .getResult();

        assertEquals(ORDER_ONE_NUMBER + "a", result.get(0, String.class));
    }

    @Test
    public void shouldSelectSubstringFrom() {
        Tuple result = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.number).andThen(substring(2)))))
                .where(Predicates.of(Predicates.equal(SellOrder_.number, ORDER_ONE_NUMBER)))
                .getSingleResult(em)
                .getResult();

        assertEquals(ORDER_ONE_NUMBER.substring(1), result.get(0, String.class));
    }

    @Test
    public void shouldSelectSubstringFromLen() {
        Tuple result = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.number).andThen(substring(2, 1)))))
                .where(Predicates.of(Predicates.equal(SellOrder_.number, ORDER_ONE_NUMBER)))
                .getSingleResult(em)
                .getResult();

        assertEquals(ORDER_ONE_NUMBER.substring(1, 2), result.get(0, String.class));
    }

    @Test
    public void shouldCombineStringExpressions() {
        Tuple result = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.number)
                        .andThen(substring(1, 1))
                        .andThen(concat("a"))
                        .andThen(upper()))))
                .where(Predicates.of(Predicates.equal(SellOrder_.number, ORDER_ONE_NUMBER)))
                .getSingleResult(em)
                .getResult();

        assertEquals(ORDER_ONE_NUMBER.substring(0, 1) + "A", result.get(0, String.class));
    }

}
