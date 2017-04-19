package com.lynx.fqb.join;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.CustomResult;
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
    public void shouldJoinOnFullQuery() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Child.class)
                .with(Selections.of(
                        Selections.attr(Child_.id),
                        Selections.attr(Child_.name)))
                .join(Joins.of(Joins.inner(Child_.parent)))
                .where(Predicates.of(Predicates.isNotNull(Paths.get(Child_.id)).andThen(Predicates.and(Predicates.isNull(Paths.get(Child_.name))))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
