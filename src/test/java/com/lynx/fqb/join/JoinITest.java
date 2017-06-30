package com.lynx.fqb.join;

import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.Item_;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.predicate.Predicates;
import com.lynx.fqb.selection.Selections;

public class JoinITest extends IntegrationTestBase {

    @Test
    public void shouldPerformRawJoin() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
        Root<Item> from = cq.from(Item.class);
        Join<Item, SellOrder> join = from.join(Item_.sellOrder);
        Path<Long> path = join.get(SellOrder_.id);

        join.on(path.in(1l));

        em.createQuery(cq).getResultList();
    }

    @Test
    public void shouldJoinOnEntitySelection() {
        BiFunction<CriteriaBuilder, Path<? extends SellOrder>, Predicate[]> predicates = Predicates.of(Predicates.in(Paths.get(SellOrder_.id), ORDER_ONE_ID));
        BiFunction<CriteriaBuilder, From<Item, Item>, Join<Item, ?>> join = Joins.join(Item_.sellOrder, JoinType.INNER, Optional.of(predicates));
        BiFunction<CriteriaBuilder, From<Item, Item>, Join<Item, ?>[]> of = Joins.of(join);

        List<Item> resultList = Select.from(Item.class)
                .join(of)
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
                        Joins.inner(Item_.sellOrder, of(contains(get(SellOrder_.number), IntegrationTestBase.ORDER_ONE_NUMBER)))))
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

}
