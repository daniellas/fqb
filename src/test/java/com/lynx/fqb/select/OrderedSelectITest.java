package com.lynx.fqb.select;

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

public class OrderedSelectITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntitiesSortedByAttributeAsc() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .orderBy(Orders.of(Orders.asc(Parent_.id)))
                .getResultList(em);

        assertAsc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByAttributeDesc() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .orderBy(Orders.of(Orders.desc(Parent_.id)))
                .getResultList(em);

        assertDesc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByPathAsc() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .orderBy(Orders.of(Orders.asc(Paths.get(Parent_.id))))
                .getResultList(em);

        assertAsc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByPathDesc() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .orderBy(Orders.of(Orders.desc(Paths.get(Parent_.id))))
                .getResultList(em);

        assertDesc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByExpressionAsc() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .orderBy(Orders.of(Orders.asc(Expressions.ofAttr(Parent_.birthDate).andThen(Expressions.year()))))
                .getResultList(em);

        assertAsc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByExpressionDesc() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .orderBy(Orders.of(Orders.desc(Expressions.ofAttr(Parent_.birthDate).andThen(Expressions.year()))))
                .getResultList(em);

        assertDesc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByParentPathAsc() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .orderBy(Orders.of(
                        Orders.asc(Paths.get(Parent_.id)),
                        (cb, root) -> {
                            return cb.asc(root.get(Parent_.birthDate));
                        }))
                .getResultList(em);

        assertAsc(resultList);
    }

    private void assertAsc(List<Parent> resultList) {
        Assert.assertEquals(1l, resultList.get(0).getId().longValue());
    }

    private void assertDesc(List<Parent> resultList) {
        Assert.assertNotEquals(1l, resultList.get(0).getId().longValue());
    }

}
