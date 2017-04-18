package com.lynx.fqb.select;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.select.Selections.*;

import java.util.List;

import javax.persistence.Tuple;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.expression.Expressions;
import com.lynx.fqb.group.Groupings;

public class GroupITest extends IntegrationTestBase {

    @Test
    public void shouldGroupBy() {
        List<Tuple> resultList = Select.tupleFrom(Parent.class)
                .with(of(fromExpr(Expressions.fromAttr(Parent_.birthDate).andThen(year()).andThen(max()))))
                .groupBy(Groupings.of(Groupings.fromAttr(Parent_.name)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
