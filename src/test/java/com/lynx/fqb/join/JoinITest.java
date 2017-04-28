package com.lynx.fqb.join;

import java.util.List;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
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
import com.lynx.fqb.select.Selections;

public class JoinITest extends IntegrationTestBase {

    // @Test
    // public void shouldJoinOnEntitySelection() {
    // List<Child> resultList = Select.from(Child.class)
    // .join(Joins.of(Joins.inner(Child_.parent)))
    // .getResultList(em);
    //
    // Assert.assertFalse(resultList.isEmpty());
    // }
    //
    // @Test
    // public void shouldJoinOnCustomSelection() {
    // List<CustomResult> resultList = Select.customFrom(CustomResult.class,
    // Child.class)
    // .with(Selections.of(
    // Selections.attr(Child_.id),
    // Selections.attr(Child_.name)))
    // .join(Joins.of(Joins.inner(Child_.parent)))
    // .getResultList(em);
    //
    // Assert.assertFalse(resultList.isEmpty());
    // }
    //
    // @Test
    // public void shouldJoinOnFullQuery() {
    // List<CustomResult> resultList = Select.customFrom(CustomResult.class,
    // Child.class)
    // .with(Selections.of(
    // Selections.attr(Child_.id),
    // Selections.attr(Child_.name)))
    // .join(Joins.of(Joins.inner(Child_.parent)))
    // .where(Predicates.of(
    // Predicates.and(
    // Predicates.isNotNull(Paths.get(Child_.id)),
    // Predicates.isNull(Paths.get(Child_.name)))))
    // .getResultList(em);
    //
    // Assert.assertFalse(resultList.isEmpty());
    // }

    @Test
    public void shoouldJoinWithPredicates() {
        BiFunction<CriteriaBuilder, Root<Parent>, Predicate[]> of = Predicates.of(Predicates.equal(Parent_.id, 1l));
        
        Select.from(Child.class)
//                .join(Joins.of(Joins.join(Child_.parent, JoinType.INNER,of)))
                .getResultList(em);
    }

}
