package com.lynx.fqb.join;

import static com.lynx.fqb.join.Joins.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.Item_;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.entity.User_;

public class JoinCascadeITest extends IntegrationTestBase {

    @Test
    public void shouldCascadeInner() {
        List<Item> resultList = Select.from(Item.class)
                .join(of(inner(Item_.sellOrder)
                        .andThen(cascadeInner(SellOrder_.creator))
                        .andThen(cascadeInner(User_.country))))
                .getResultList(em);

        assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldCascadeLeft() {
        List<Item> resultList = Select.from(Item.class)
                .join(of(left(Item_.sellOrder)
                        .andThen(cascadeLeft(SellOrder_.creator))
                        .andThen(cascadeLeft(User_.country))))
                .getResultList(em);

        assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldCascadeMixed() {
        List<Item> resultList = Select.from(Item.class)
                .join(of(
                        inner(Item_.sellOrder).andThen(cascadeInner(SellOrder_.creator)),
                        inner(Item_.sellOrder).andThen(cascadeLeft(SellOrder_.supervisor))))
                .getResultList(em);

        assertFalse(resultList.isEmpty());
    }
}
