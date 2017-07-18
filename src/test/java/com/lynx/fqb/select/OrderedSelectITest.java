package com.lynx.fqb.select;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        assertOrder(resultList, Comparator.comparing(SellOrder::getId), SellOrder[]::new);
    }

    @Test
    public void shouldSelectEntitiesSortedByVarArg() {
        assertListResultNotEmpty().accept(Select
                .from(SellOrder.class)
                .orderBy(Orders.asc(SellOrder_.id)));
    }

    @Test
    public void shouldSelectEntitiesSortedByAttributeDesc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.desc(SellOrder_.id)))
                .getResultList(em);

        assertOrder(resultList, Comparator.comparing(SellOrder::getId).reversed(), SellOrder[]::new);
    }

    @Test
    public void shouldSelectEntitiesSortedByPathAsc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.asc(Paths.get(SellOrder_.id))))
                .getResultList(em);

        assertOrder(resultList, Comparator.comparing(SellOrder::getId), SellOrder[]::new);
    }

    @Test
    public void shouldSelectEntitiesSortedByPathDesc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.desc(Paths.get(SellOrder_.id))))
                .getResultList(em);

        assertOrder(resultList, Comparator.comparing(SellOrder::getId).reversed(), SellOrder[]::new);
    }

    @Test
    public void shouldSelectEntitiesSortedByExpressionAsc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.asc(Expressions.ofAttr(SellOrder_.dueDate).andThen(Expressions.year()))))
                .getResultList(em);

        assertOrder(resultList, Comparator.comparing((SellOrder o) -> getYear(o.getDueDate())), SellOrder[]::new);
    }

    @Test
    public void shouldSelectEntitiesSortedByExpressionDesc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.desc(Expressions.ofAttr(SellOrder_.dueDate).andThen(Expressions.year()))))
                .getResultList(em);

        assertOrder(resultList, Comparator.comparing((SellOrder o) -> getYear(o.getDueDate())).reversed(),
                SellOrder[]::new);
    }

    @Test
    public void shouldSelectEntitiesSortedByParentPathAsc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.asc(Paths.get(SellOrder_.dateCreate))))
                .getResultList(em);

        assertOrder(
                resultList,
                Comparator.comparing(SellOrder::getDateCreate),
                SellOrder[]::new);
    }

    @Test
    public void shouldSelectEntitiesSortedByParentAttributeAsc() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .orderBy(Orders.of(Orders.asc(SellOrder_.dateCreate)))
                .getResultList(em);

        assertOrder(
                resultList,
                Comparator.comparing(SellOrder::getDateCreate),
                SellOrder[]::new);
    }

    private int getYear(Date date) {
        return Optional.ofNullable(date)
                .map(d -> {
                    Calendar cal = Calendar.getInstance();

                    cal.setTime(date);

                    return cal.get(Calendar.YEAR);
                })
                .orElse(0);
    }
}
