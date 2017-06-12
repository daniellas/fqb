package com.lynx.fqb.select;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.selection.Selections.*;

import java.util.List;

import javax.persistence.Tuple;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.expression.Expressions;
import com.lynx.fqb.group.Groupings;
import com.lynx.fqb.path.Paths;

public class GroupedSelectITest extends IntegrationTestBase {

    @Test
    public void shouldGroupByAttribute() {
        List<Tuple> resultList = Select.tupleFrom(SellOrder.class)
                .with(of(expr(Expressions.ofAttr(SellOrder_.dueDate).andThen(year()).andThen(max()))))
                .groupBy(Groupings.of(Groupings.byAttr(SellOrder_.number)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldGroupByPath() {
        List<Tuple> resultList = Select.tupleFrom(SellOrder.class)
                .with(of(path(Paths.get(SellOrder_.number))))
                .groupBy(Groupings.of(Groupings.byPath(Paths.get(SellOrder_.number))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldGroupByExpression() {
        List<Tuple> resultList = Select.tupleFrom(SellOrder.class)
                .with(of(expr(Expressions.ofAttr(SellOrder_.id).andThen(sum()))))
                .groupBy(Groupings.of(Groupings.byExpr(Expressions.ofAttr(SellOrder_.dueDate).andThen(Expressions.month()))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
