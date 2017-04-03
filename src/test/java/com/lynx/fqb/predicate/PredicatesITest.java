package com.lynx.fqb.predicate;

import java.util.List;

import javax.persistence.Tuple;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.select.Selections;

import static com.lynx.fqb.predicate.Predicates.*;

public class PredicatesITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntitiesRestricted() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .where(of(equal(Parent_.id, 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultRestricted() {
        List<CustomResult> resultList = Select.as(CustomResult.class)
                .from(Parent.class)
                .with(Selections.ofAttributes(Parent_.id, Parent_.name))
                .where(of(equal(Parent_.id, 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectTupleRestricted() {
        List<Tuple> resultList = Select.tuple()
                .from(Parent.class)
                .with(Selections.ofAttributes(Parent_.id, Parent_.name))
                .where(of(equal(Parent_.id, 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
