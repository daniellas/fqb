package com.lynx.fqb.select;

import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.select.Selections.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;

public class SelectionsITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntities() {
        List<Parent> resultList = Select.from(Parent.class).getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultsFromAttributes() {
        List<CustomResult> resultList = Select.as(CustomResult.class)
                .from(Parent.class)
                .with(ofAttributes(Parent_.id, Parent_.name))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultsFromPaths() {
        List<CustomResult> resultList = Select.as(CustomResult.class)
                .from(Parent.class)
                .with(ofPaths(get(Parent_.id), get(Parent_.name)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultsFromNestedPaths() {
        List<CustomResult> resultList = Select.as(CustomResult.class)
                .from(Child.class)
                .with(ofPaths(get(Child_.id), get(Child_.parent).andThen(get(Parent_.name))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
