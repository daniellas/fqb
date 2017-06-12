package com.lynx.fqb.util;

import static com.lynx.fqb.util.QueryBuilder.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.SellOrder;

public class QueryBuilderITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntities() {
        getCriteriaBuilder()
                .andThen(createCriteriaQuery(SellOrder.class, SellOrder.class))
                .andThen(applyRoot(SellOrder.class))
                .andThen(createTypedQuery(em))
                .apply(em)
                .getResultList();
    }
}
