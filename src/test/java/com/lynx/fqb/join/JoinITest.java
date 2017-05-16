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

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.predicate.Predicates;
import com.lynx.fqb.selection.Selections;

public class JoinITest extends IntegrationTestBase {

    @Test
    public void shouldPerformRawJoin() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Child> cq = cb.createQuery(Child.class);
        Root<Child> from = cq.from(Child.class);
        Join<Child, Parent> join = from.join(Child_.parent);
        Path<Long> path = join.get(Parent_.id);

        join.on(path.in(1l));

        em.createQuery(cq).getResultList();
    }

    @Test
    public void shouldJoinOnEntitySelection() {
        BiFunction<CriteriaBuilder, Path<? extends Parent>, Predicate[]> predicates = Predicates.of(Predicates.in(Paths.get(Parent_.id), 1l));
        BiFunction<CriteriaBuilder, From<Child, Child>, Join<Child, ?>> join = Joins.join(Child_.parent, JoinType.INNER, Optional.of(predicates));
        BiFunction<CriteriaBuilder, From<Child, Child>, Join<Child, ?>[]> of = Joins.of(join);

        List<Child> resultList = Select.from(Child.class)
                .join(of)
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldJoinOnCustomSelection() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Child.class)
                .with(Selections.of(
                        Selections.attr(Child_.id),
                        Selections.attr(Child_.name)))
                .join(Joins.of(Joins.inner(Child_.parent)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldJoinWithWhere() {
        List<Tuple> resultList = Select.tupleFrom(Child.class)
                .with(Selections.of(
                        Selections.attr(Child_.id),
                        Selections.attr(Child_.name)))
                .join(Joins.of(Joins.inner(Child_.parent)))
                .where(of(and(
                        isNotNull(get(Child_.id)),
                        isNull(get(Child_.name)))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldJoinWithRestrictionsAndWhere() {
        List<Tuple> resultList = Select.tupleFrom(Child.class)
                .with(Selections.of(
                        Selections.attr(Child_.id),
                        Selections.attr(Child_.name)))
                .join(Joins.of(
                        Joins.inner(Child_.parent, of(contains(get(Parent_.name), "a")))))
                .where(of(and(isNotNull(get(Child_.id)),
                        isNull(get(Child_.name)))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldJoinWithSinglePredicate() {
        List<Child> resultList = Select.from(Child.class)
                .join(Joins.of(Joins.inner(Child_.parent, of(equal(get(Parent_.id), 1l)))))
                .getResultList(em);

        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void shouldJoinWithMultiplePredicates() {
        List<Child> resultList = Select.from(Child.class)
                .join(Joins.of(Joins.inner(Child_.parent,
                        of(or(
                                equal(get(Parent_.id), 1l),
                                equal(get(Parent_.id), 2l))))))
                .getResultList(em);

        Assert.assertEquals(1, resultList.size());
    }
}
