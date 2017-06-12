package com.lynx.fqb.expression;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.selection.Selections.*;

import java.util.List;

import javax.persistence.Tuple;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.CustomNumberResult;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.select.SingleResult;

public class ExpressionITest extends IntegrationTestBase {

    @Test
    public void shouldCombineExpressions() {
        SingleResult<CustomNumberResult> result = Select.customFrom(CustomNumberResult.class, SellOrder.class).with(of(expr(
                Expressions.ofAttr(SellOrder_.dueDate)
                        .andThen(year())
                        .andThen(sum())
                        .andThen(sum(20)))))
                .getSingleResult(em);

        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void shouldSelectEntityCountAndThenSum() {
        Assert.assertNotNull(Select.customFrom(Long.class, SellOrder.class).with(of(expr(count(SellOrder.class).andThen(sum(1l))))).getSingleResult(em));
    }

    @Test
    public void shouldSelectEntityCountAndThenSumDiff() {
        Assert.assertNotNull(Select.customFrom(Long.class, SellOrder.class)
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
                        expr(ofValue(1l)),
                        attr(SellOrder_.number)))
                .getResultList(em);

        Assert.assertEquals(resultList.size(), sumId(resultList).intValue());
    }

    @Test
    public void shouldSelectIdProdByValue() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, SellOrder.class)
                .with(of(
                        expr(ofAttr(SellOrder_.id).andThen(prod(1l))),
                        attr(SellOrder_.number)))
                .getResultList(em);

        Assert.assertEquals(5l, sumId(resultList).longValue());
    }

    @Test
    public void shouldSelectIdProdByAttribute() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, SellOrder.class)
                .with(of(
                        expr(ofAttr(SellOrder_.id).andThen(prod(SellOrder_.id))),
                        attr(SellOrder_.number)))
                .getResultList(em);

        Assert.assertEquals(17l, sumId(resultList).longValue());
    }

    @Test
    public void shouldSelectAverage() {
        SingleResult<Tuple> singleResult = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.id).andThen(Expressions.avg()))))
                .getSingleResult(em);

        Assert.assertTrue(singleResult.isPresent());
        Assert.assertEquals(2.5, singleResult.getResult().get(0));
    }

    @Test
    public void shouldSelectMin() {
        SingleResult<Tuple> singleResult = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.id).andThen(Expressions.min()))))
                .getSingleResult(em);

        Assert.assertTrue(singleResult.isPresent());
        Assert.assertEquals(1l, singleResult.getResult().get(0));
    }

    @Test
    public void shouldSelectCount() {
        SingleResult<Tuple> singleResult = Select.tupleFrom(SellOrder.class)
                .with(of(expr(ofAttr(SellOrder_.id).andThen(Expressions.count()))))
                .getSingleResult(em);

        Assert.assertTrue(singleResult.isPresent());
        Assert.assertEquals(2l, singleResult.getResult().get(0));
    }

    private Long sumId(List<CustomResult> resultList) {
        return resultList.stream()
                .map(CustomResult::getId)
                .reduce(0l, Long::sum);
    }
}
