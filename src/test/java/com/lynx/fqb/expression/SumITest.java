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

public class SumITest extends IntegrationTestBase {

    @Test
    public void shouldCombineSum() {
        Tuple result = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.id)
                        .andThen(sum(SellOrder_.id))
                        .andThen(sum(10l)))))
                .where(Predicates.of(Predicates.equal(SellOrder_.id, ORDER_ONE_ID)))
                .getSingleResult(em)
                .getResult();

        assertEquals(ORDER_ONE_ID * 2 + 10, result.get(0, Long.class).longValue());
    }

    @Test
    public void shouldCombineSumWithLiteral() {
        Tuple result = Select.tupleFrom(SellOrder.class)
                .with(of(
                        expr(ofValue(0l)),
                        expr(ofAttr(SellOrder_.id)
                                .andThen(sum(10l)))))
                .where(Predicates.of(Predicates.equal(SellOrder_.id, ORDER_ONE_ID)))
                .getSingleResult(em)
                .getResult();

        assertEquals(0l, result.get(0, Long.class).longValue());
        assertEquals(ORDER_ONE_ID + 10l, result.get(1, Long.class).longValue());

    }

}
