package com.lynx.fqb.join;

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
                .join(Joins.of(Joins.fetchInner(Item_.sellOrder)))
                .getResultList(em);
    }

    @Test
    public void shouldFetchLeft() {
        Select.from(Item.class)
                .join(Joins.of(Joins.fetchLeft(Item_.sellOrder)))
                .getResultList(em);
    }

    @Test
    public void shouldCascadeFetchInner() {
        Select.from(Item.class)
                .join(Joins.of(
                        Joins.fetchInner(Item_.sellOrder).andThen(Joins.cascadeFetchInner(SellOrder_.creator))))
                .getResultList(em);
    }

    @Test
    public void shouldCascadeFetchLeft() {
        Select.from(Item.class)
                .join(Joins.of(
                        Joins.fetchInner(Item_.sellOrder).andThen(Joins.cascadeFetchLeft(SellOrder_.creator))))
                .getResultList(em);
    }

}
