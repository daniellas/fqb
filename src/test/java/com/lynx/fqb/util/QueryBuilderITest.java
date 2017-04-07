package com.lynx.fqb.util;

import static com.lynx.fqb.util.QueryBuilder.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Parent;

public class QueryBuilderITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntities() {
        getCriteriaBuilder()
                .andThen(createCriteriaQuery(Parent.class, Parent.class))
                .andThen(applyRoot(Parent.class))
                .andThen(createTypedQuery(em))
                .apply(em)
                .getResultList();
    }
}
