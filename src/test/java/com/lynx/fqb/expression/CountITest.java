package com.lynx.fqb.expression;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.select.Selections.*;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;

public class CountITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntityCount() {
        Assert.assertNotNull(Select.customFrom(Long.class, Parent.class).with(of(fromExpr(count(Parent.class)))).getSingleResult(em));
    }

    @Test
    public void shouldSelectEntityCountAndThenSum() {
        Assert.assertNotNull(Select.customFrom(Long.class, Parent.class).with(of(fromExpr(count(Parent.class).andThen(sum(1l))))).getSingleResult(em));
    }

    @Test
    public void shouldSelectEntityCountAndThenSumDiff() {
        Assert.assertNotNull(Select.customFrom(Long.class, Parent.class)
                .with(of(fromExpr(
                        count(Parent.class)
                                .andThen(sum(1l))
                                .andThen(diff(1l)))))
                .getSingleResult(em));
    }

    @Test
    public void shouldSelectEntityDistinctCount() {
        Assert.assertNotNull(Select.customFrom(Long.class, Parent.class).with(of(fromExpr(countDistinct(Parent.class)))).getSingleResult(em));
    }

    @Test
    public void shouldSelectPathCount() {
        Assert.assertNotNull(Select.customFrom(Long.class, Parent.class).with(of(fromExpr(count(get(Parent_.name))))).getSingleResult(em));
    }

    @Test
    public void shouldSelectPathDistinctCount() {
        Assert.assertNotNull(Select.customFrom(Long.class, Parent.class).with(of(fromExpr(countDistinct(get(Parent_.name))))).getSingleResult(em));
    }

}
