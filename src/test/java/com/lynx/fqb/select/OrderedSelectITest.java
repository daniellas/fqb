package com.lynx.fqb.select;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.expression.Expressions;
import com.lynx.fqb.order.Orders;
import com.lynx.fqb.path.Paths;

public class OrderedSelectITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntitiesSortedByAttributeAsc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.asc(SellOrder_.id)))
                .getResultList(em);

        assertAsc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByAttributeDesc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.desc(SellOrder_.id)))
                .getResultList(em);

        assertDesc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByPathAsc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.asc(Paths.get(SellOrder_.id))))
                .getResultList(em);

        assertAsc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByPathDesc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.desc(Paths.get(SellOrder_.id))))
                .getResultList(em);

        assertDesc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByExpressionAsc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.asc(Expressions.ofAttr(SellOrder_.dueDate).andThen(Expressions.year()))))
                .getResultList(em);

        assertAsc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByExpressionDesc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.desc(Expressions.ofAttr(SellOrder_.dueDate).andThen(Expressions.year()))))
                .getResultList(em);

        assertDesc(resultList);
    }

    @Test
    public void shouldSelectEntitiesSortedByParentPathAsc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(
                        Orders.asc(Paths.get(SellOrder_.id)),
                        (cb, root) -> {
                            return cb.asc(root.get(SellOrder_.dateCreate));
                        }))
                .getResultList(em);

        assertAsc(resultList);
    }

    private void assertAsc(List<SellOrder> resultList) {
        Assert.assertEquals(ORDER_ONE_ID, resultList.get(0).getId());
    }

    private void assertDesc(List<SellOrder> resultList) {
        Assert.assertEquals(ORDER_TWO_ID, resultList.get(0).getId());
    }

}
