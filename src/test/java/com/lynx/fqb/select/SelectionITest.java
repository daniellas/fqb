package com.lynx.fqb.select;

import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.select.Selections.*;

import java.util.List;

import javax.persistence.Tuple;

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

public class SelectionITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntities() {
        List<Parent> resultList = Select.from(Parent.class).getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectPagedEntities() {
        List<Parent> resultList = Select.from(Parent.class).getResultList(em, 0, 1);

        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void shouldSelectDistinctEntities() {
        List<Parent> resultList = Select.distinct(Parent.class).getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResults() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Parent.class)
                .with(of(path(get(Parent_.id)), path(get(Parent_.name))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultsFromNestedPaths() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Child.class)
                .with(of(path(get(Child_.id)), path(get(Child_.parent).andThen(get(Parent_.name)))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectAttrFromSuperType() {
        List<Tuple> resultList = Select.tupleFrom(Parent.class).with(Selections.of(Selections.attr(Parent_.dateCreate))).getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectPathFromSuperType() {
        List<Tuple> resultList = Select.tupleFrom(Parent.class).with(Selections.of(Selections.path(Paths.get(Parent_.dateCreate)))).getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
