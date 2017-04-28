package com.lynx.fqb.join;

import static com.lynx.fqb.join.Joins.*;
import static com.lynx.fqb.join.Joins.of;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;
import static com.lynx.fqb.predicate.Predicates.of;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

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
import com.lynx.fqb.select.Selections;

public class JoinITest extends IntegrationTestBase {

    @Test
    public void shouldJoinOnEntitySelection() {
        List<Child> resultList = Select.from(Child.class)
                .join(Joins.of(Joins.inner(Child_.parent)))
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
                        Joins.inner(Child_.parent,
                                of(contains(get(Child_.parent).get(Parent_.name), "a")))))
                .where(of(and(isNotNull(get(Child_.id)),
                        isNull(get(Child_.name)))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldJoinWithSinglePredicate() {
        List<Child> resultList = Select.from(Child.class)
                .join(of(inner(Child_.parent,
                        of(equal(get(Child_.parent).get(Parent_.id), 1l)))))
                .getResultList(em);

        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void shouldJoinWithMultiplePredicates() {
        List<Child> resultList = Select.from(Child.class)
                .join(of(inner(Child_.parent,
                        of(or(
                                equal(get(Child_.parent).get(Parent_.id), 1l),
                                equal(get(Child_.parent).get(Parent_.id), 2l))))))
                .getResultList(em);

        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void shouldInnerJoinOnListAttribute() {
        List<Parent> resultList = Select.from(Parent.class)
                .join(of(join(Parent_.children, JoinType.INNER, Optional.empty())))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldInnerJoinOnListAttributeWithPredicates() {
        BiFunction<CriteriaBuilder, Path<Child>, Predicate[]> predicates = (cb, root) -> {
            return new Predicate[] { root.get(Child_.id).in(1l, 2l) };
        };

        List<Parent> resultList = Select.from(Parent.class)
                .join(of(join(Parent_.children, JoinType.INNER, Optional.of(predicates))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
