package com.lynx.fqb.expression;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.selection.Selections.*;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.CustomNumberResult;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;

public class FunctionITest extends IntegrationTestBase {

    @Test
    public void shouldSelectYear() {
        Assert.assertNotNull(
                Select.customFrom(CustomNumberResult.class, Parent.class).with(of(expr(Expressions.ofAttr(Parent_.birthDate).andThen(year()))))
                        .getSingleResult(em));
    }

    @Test
    public void shouldSelectMonth() {
        Assert.assertNotNull(
                Select.customFrom(CustomNumberResult.class, Parent.class).with(of(expr(Expressions.ofAttr(Parent_.birthDate).andThen(month()))))
                        .getSingleResult(em));
    }

    @Test
    public void shouldSelectDayOfMonth() {
        Assert.assertNotNull(
                Select.customFrom(CustomNumberResult.class, Parent.class).with(of(expr(Expressions.ofAttr(Parent_.birthDate)
                        .andThen(dayOfMonth()))))
                        .getSingleResult(em));
    }

}
