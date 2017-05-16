package com.lynx.fqb.select;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.selection.Selections.*;

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
import com.lynx.fqb.predicate.Predicates;

public class GroupedRestrictedSelectITest extends IntegrationTestBase {

    @Test
    public void shouldGroupRestrictByAttribute() {
        List<Tuple> resultList = Select.tupleFrom(Parent.class)
                .with(of(expr(Expressions.ofAttr(Parent_.birthDate).andThen(year()).andThen(max()))))
                .groupBy(Groupings.of(Groupings.byAttr(Parent_.name)))
                .having(Predicates.of(Predicates.gt(Expressions.ofAttr(Parent_.birthDate).andThen(year()).andThen(max()), 0)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
