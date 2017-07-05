package com.lynx.fqb.join;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class JoinListITest extends IntegrationTestBase {

    @Test
    public void shouldJoinInnerOnList() {
        List<SellOrder> resultList = Select.from(SellOrder.class)
                .join(Joins.of(Joins.inner(SellOrder_.items)))
                .getResultList(em);

        assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldJoinLeftOnList() {
        List<SellOrder> resultList = Select.from(SellOrder.class)
                .join(Joins.of(Joins.left(SellOrder_.items)))
                .getResultList(em);

        assertFalse(resultList.isEmpty());
    }

}
