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
import com.lynx.fqb.path.Paths;

public class GroupedSelectITest extends IntegrationTestBase {

    @Test
    public void shouldGroupByAttribute() {
        List<Tuple> resultList = Select.tupleFrom(Parent.class)
                .with(of(expr(Expressions.ofAttr(Parent_.birthDate).andThen(year()).andThen(max()))))
                .groupBy(Groupings.of(Groupings.byAttr(Parent_.name)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldGroupByPath() {
        List<Tuple> resultList = Select.tupleFrom(Parent.class)
                .with(of(path(Paths.get(Parent_.name))))
                .groupBy(Groupings.of(Groupings.byPath(Paths.get(Parent_.name))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldGroupByExpression() {
        List<Tuple> resultList = Select.tupleFrom(Parent.class)
                .with(of(expr(Expressions.ofAttr(Parent_.id).andThen(sum()))))
                .groupBy(Groupings.of(Groupings.byExpr(Expressions.ofAttr(Parent_.birthDate).andThen(Expressions.month()))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
