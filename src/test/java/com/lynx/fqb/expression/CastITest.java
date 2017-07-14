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

public class CastITest extends IntegrationTestBase {

    @Test
    public void shouldCastLongToString() {
        Tuple result = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.id)
                        .andThen(as(String.class)))))
                .where(Predicates.of(Predicates.equal(SellOrder_.id, ORDER_ONE_ID)))
                .getSingleResult(em)
                .getResult();

        assertEquals(ORDER_ONE_ID.toString(), result.get(0, String.class));
    }

}
