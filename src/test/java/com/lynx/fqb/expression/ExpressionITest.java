package com.lynx.fqb.expression;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.selection.Selections.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Tuple;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.CustomNumberResult;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.result.SingleResult;

public class ExpressionITest extends IntegrationTestBase {

    @Test
    public void shouldCombineExpressions() {
        SingleResult<CustomNumberResult> result = Select.customFrom(CustomNumberResult.class, SellOrder.class)
                .with(of(expr(
                        Expressions.ofAttr(SellOrder_.dueDate)
                                .andThen(year())
                                .andThen(sum())
                                .andThen(sum(20)))))
                .getSingleResult(em);

        assertTrue(result.isPresent());
    }

    @Test
    public void shouldSelectEntityCountAndThenSum() {
        assertNotNull(Select.customFrom(Long.class, SellOrder.class)
                .with(of(expr(count(SellOrder.class).andThen(sum(1l))))).getSingleResult(em));
    }

    @Test
    public void shouldSelectEntityCountAndThenSumDiff() {
        assertNotNull(Select.customFrom(Long.class, SellOrder.class)
                .with(of(expr(
                        count(SellOrder.class)
                                .andThen(sum(1l))
                                .andThen(diff(1l)))))
                .getSingleResult(em));
    }

    @Test
    public void shouldSelectLiteralExpression() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, SellOrder.class)
                .with(of(
                        expr(ofValue(BigDecimal.ONE)),
                        attr(SellOrder_.number)))
                .getResultList(em);

        assertEquals(resultList.size(), sumId(resultList).intValue());
    }

    @Test
    public void shouldSelectIdProdByValue() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, SellOrder.class)
                .with(of(
                        expr(ofAttr(SellOrder_.total).andThen(prod(BigDecimal.ONE))),
                        attr(SellOrder_.number)))
                .getResultList(em);

        assertEquals(11l, sumId(resultList).longValue());
    }

    @Test
    public void shouldSelectIdProdByAttribute() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, SellOrder.class)
                .with(of(
                        expr(ofAttr(SellOrder_.total).andThen(prod(SellOrder_.total))),
                        attr(SellOrder_.number)))
                .getResultList(em);

        assertEquals(101l, sumId(resultList).longValue());
    }

    @Test
    public void shouldSelectAverage() {
        SingleResult<Tuple> singleResult = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.total).andThen(Expressions.avg()))))
                .getSingleResult(em);

        assertTrue(singleResult.isPresent());
        assertEquals(5.5, singleResult.getResult().get(0));
    }

    @Test
    public void shouldSelectMin() {
        SingleResult<Tuple> singleResult = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.total).andThen(Expressions.min()))))
                .getSingleResult(em);

        assertTrue(singleResult.isPresent());
        assertEquals(1l, singleResult.getResult().get(0, BigDecimal.class).longValue());
    }

    @Test
    public void shouldSelectCount() {
        SingleResult<Tuple> singleResult = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.id).andThen(count()))))
                .getSingleResult(em);

        assertTrue(singleResult.isPresent());
        assertEquals(2l, singleResult.getResult().get(0));
    }

    private BigDecimal sumId(List<CustomResult> resultList) {
        return resultList.stream()
                .map(CustomResult::getNumber)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
