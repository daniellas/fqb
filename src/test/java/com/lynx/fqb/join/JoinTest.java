package com.lynx.fqb.join;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

import org.junit.Test;

import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.Item_;
import com.lynx.fqb.entity.SellOrder_;

public class JoinTest {

    @Test
    public void shouldChainJoins() {
//        Joins.join(Item_.sellOrder, JoinType.INNER, Optional.empty())
//                .andThen(j -> {
//                    return j.join(SellOrder_.creator);
//                });
    }
}
