package com.lynx.fqb.expression;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.select.Selections.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.CustomNumberResult;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.select.SingleResult;

public class ExpressionITest extends IntegrationTestBase {

    @Test
    public void shouldCombineExpressions() {
        SingleResult<CustomNumberResult> result = Select.customFrom(CustomNumberResult.class, Parent.class).with(of(expr(
                Expressions.ofAttr(Parent_.birthDate)
                        .andThen(year())
                        .andThen(sum())
                        .andThen(sum(20)))))
                .getSingleResult(em);

        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void shouldSelectEntityCountAndThenSum() {
        Assert.assertNotNull(Select.customFrom(Long.class, Parent.class).with(of(expr(count(Parent.class).andThen(sum(1l))))).getSingleResult(em));
    }

    @Test
    public void shouldSelectEntityCountAndThenSumDiff() {
        Assert.assertNotNull(Select.customFrom(Long.class, Parent.class)
                .with(of(expr(
                        count(Parent.class)
                                .andThen(sum(1l))
                                .andThen(diff(1l)))))
                .getSingleResult(em));
    }

    @Test
    public void shouldSelectLiteralExpression() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Parent.class)
                .with(of(
                        expr(ofValue(1l)),
                        attr(Parent_.name)))
                .getResultList(em);

        Assert.assertEquals(resultList.size(), sumId(resultList).intValue());
    }

    @Test
    public void shouldSelectIdProdByValue() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Parent.class)
                .with(of(
                        expr(ofAttr(Parent_.id).andThen(prod(1l))),
                        attr(Parent_.name)))
                .getResultList(em);

        Assert.assertEquals(4l, sumId(resultList).longValue());
    }

    @Test
    public void shouldSelectIdProdByAttribute() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Parent.class)
                .with(of(
                        expr(ofAttr(Parent_.id).andThen(prod(Parent_.id))),
                        attr(Parent_.name)))
                .getResultList(em);

        Assert.assertEquals(10l, sumId(resultList).longValue());
    }

    private Long sumId(List<CustomResult> resultList) {
        return resultList.stream()
                .map(CustomResult::getId)
                .reduce(0l, Long::sum);
    }
}
