package com.lynx.fqb.join;

import javax.persistence.criteria.JoinType;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.Item_;
import com.lynx.fqb.entity.SellOrder_;

public class FetchITest extends IntegrationTestBase {

    @Test
    public void shouldFetchInner() {
        Select.from(Item.class)
                .join(Joins.of(Joins.fetch(Item_.sellOrder, JoinType.INNER)))
                .getResultList(em);
    }

    @Test
    public void shouldFetchLeft() {
        Select.from(Item.class)
                .join(Joins.of(Joins.fetch(Item_.sellOrder, JoinType.LEFT)))
                .getResultList(em);
    }

    @Test
    public void shouldCascadeFetchInner() {
        Select.from(Item.class)
                .join(Joins.of(
                        Joins.fetch(Item_.sellOrder, JoinType.INNER)
                                .andThen(Joins.cascadeFetch(SellOrder_.creator, JoinType.INNER))))
                .getResultList(em);
    }

}
