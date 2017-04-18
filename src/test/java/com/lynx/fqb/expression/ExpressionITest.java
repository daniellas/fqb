package com.lynx.fqb.expression;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.select.Selections.*;
import static com.lynx.fqb.select.Selections.of;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.CustomNumberResult;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.select.SingleResult;

public class ExpressionITest extends IntegrationTestBase {

    @Test
    public void shouldCombineExpressions() {
        SingleResult<CustomNumberResult> result = Select.customFrom(CustomNumberResult.class, Parent.class).with(of(fromExpr(
                Expressions.fromAttr(Parent_.birthDate)
                        .andThen(year())
                        .andThen(sum())
                        .andThen(sum(20)))))
                .getSingleResult(em);
        
        Assert.assertTrue(result.isPresent());
    }
}
