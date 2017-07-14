package com.lynx.fqb.join;

import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;
import static com.lynx.fqb.predicate.Predicates.contains;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;
import javax.persistence.criteria.JoinType;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.Item_;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.predicate.Predicates;
import com.lynx.fqb.selection.Selections;

public class JoinITest extends IntegrationTestBase {

    @Test
    public void shouldJoinOnEntitySelection() {
        List<Item> resultList = Select.from(Item.class)
                .join(Joins.of(Joins.join(Item_.sellOrder, JoinType.INNER,
                        Optional.of(Predicates.of(Predicates.in(Paths.get(SellOrder_.id), ORDER_ONE_ID))))))
                .getResultList(em);

        assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldJoinOnCustomSelection() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Item.class)
                .with(Selections.of(
                        Selections.attr(Item_.price),
                        Selections.attr(Item_.name)))
                .join(Joins.of(Joins.inner(Item_.sellOrder)))
                .getResultList(em);

        assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldJoinWithWhere() {
        List<Tuple> resultList = Select.tupleFrom(Item.class)
                .with(Selections.of(
                        Selections.attr(Item_.id),
                        Selections.attr(Item_.name)))
                .join(Joins.of(Joins.inner(Item_.sellOrder)))
                .where(of(and(
                        isNotNull(get(Item_.id)),
                        isNotNull(get(Item_.name)))))
                .getResultList(em);

        assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldJoinWithRestrictionsAndWhere() {
        List<Tuple> resultList = Select.tupleFrom(Item.class)
                .with(Selections.of(
                        Selections.attr(Item_.id),
                        Selections.attr(Item_.name)))
                .join(Joins.of(
                        Joins.inner(Item_.sellOrder,
                                of(contains(get(SellOrder_.number), IntegrationTestBase.ORDER_ONE_NUMBER)))))
                .where(of(and(isNotNull(get(Item_.id)),
                        isNotNull(get(Item_.name)))))
                .getResultList(em);

        assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldJoinWithSinglePredicate() {
        List<Item> resultList = Select.from(Item.class)
                .join(Joins.of(Joins.inner(Item_.sellOrder, of(equal(get(SellOrder_.id), ORDER_ONE_ID)))))
                .getResultList(em);

        assertEquals(2, resultList.size());
    }

    @Test
    public void shouldJoinWithMultiplePredicates() {
        List<Item> resultList = Select.from(Item.class)
                .join(Joins.of(Joins.inner(Item_.sellOrder,
                        of(or(
                                equal(get(SellOrder_.id), ORDER_ONE_ID),
                                equal(get(SellOrder_.id), ORDER_TWO_ID))))))
                .getResultList(em);

        assertThat(resultList.size(), is(3));
    }

    @Test
    public void shouldJoinLeftWithSinglePredicate() {
        List<Item> resultList = Select.from(Item.class)
                .join(Joins.of(Joins.left(Item_.sellOrder, of(equal(get(SellOrder_.id), ORDER_ONE_ID)))))
                .getResultList(em);

        assertEquals(3, resultList.size());
    }

}
