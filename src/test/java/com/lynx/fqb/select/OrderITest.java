package com.lynx.fqb.select;

import static com.lynx.fqb.predicate.Predicates.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.expression.Expressions;
import com.lynx.fqb.order.Orders;
import com.lynx.fqb.path.Paths;

public class OrderITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntitiesSortedAsc() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .where(of(equal(Paths.get(Parent_.id), 1l)))
                .orderBy(Orders.of(Orders.asc(Paths.get(Parent_.id))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectEntitiesSortedDesc() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .where(of(equal(Paths.get(Parent_.id), 1l)))
                .orderBy(Orders.of(Orders.desc(Paths.get(Parent_.id))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectEntitiesSortedByExpression() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .where(of(equal(Paths.get(Parent_.id), 1l)))
                .orderBy(Orders.of(Orders.asc(Expressions.ofAttr(Parent_.birthDate).andThen(Expressions.year()))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
