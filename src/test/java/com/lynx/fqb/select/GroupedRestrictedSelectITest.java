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
import com.lynx.fqb.predicate.Predicates;

public class GroupedRestrictedSelectITest extends IntegrationTestBase {

    @Test
    public void shouldGroupRestrictByAttribute() {
        List<Tuple> resultList = Select.tupleFrom(SellOrder.class)
                .with(of(expr(Expressions.ofAttr(SellOrder_.dueDate).andThen(year()).andThen(max()))))
                .groupBy(Groupings.of(Groupings.byAttr(SellOrder_.number)))
                .having(Predicates
                        .of(Predicates.gt(Expressions.ofAttr(SellOrder_.dueDate).andThen(year()).andThen(max()), 0)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldGroupRestrictByAttributeVarArags() {
        assertListResultNotEmpty().accept(Select.tupleFrom(SellOrder.class)
                .with(of(expr(Expressions.ofAttr(SellOrder_.dueDate).andThen(year()).andThen(max()))))
                .groupBy(Groupings.of(Groupings.byAttr(SellOrder_.number)))
                .having(Predicates.gt(Expressions.ofAttr(SellOrder_.dueDate).andThen(year()).andThen(max()), 0)));
    }

}
