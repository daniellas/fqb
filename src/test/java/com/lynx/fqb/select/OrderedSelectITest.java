package com.lynx.fqb.select;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.sort.Sorts.*;

public class OrderedSelectITest extends IntegrationTestBase {

    @Test
    public void shouldSortBySort() {
        Select.using(em).from(Parent.class).orderBy(by(Parent_.id).reversed()).list();
    }

    @Test
    public void shouldSortBySortsStream() {
        Select.using(em).from(Parent.class)
                .orderBy(Stream.of(by(Parent_.id), by(Parent_.name)))
                .list();
    }

    @Test
    public void shouldSortBySortsList() {
        Select.using(em).from(Parent.class)
                .orderBy(Arrays.asList(by(Parent_.id), by(Parent_.name)))
                .list();
    }

    @Test
    public void shouldSortByPathSelector() {
        Select.using(em).from(Child.class).orderBy(by(get(Child_.parent).get(Parent_.id))).list();
    }

    @Test
    public void shouldSortByPathSelectorReversed() {
        Select.using(em).from(Child.class).orderBy(by(get(Child_.parent).get(Parent_.id)).reversed()).list();
    }

    @Test
    public void shouldSortByMultiplePathSelectors() {
        Select.using(em).from(Child.class).orderBy(
                Stream.of(
                        by(get(Child_.parent).get(Parent_.id)),
                        by(get(Child_.id))))
                .list();
    }

    @Test
    public void shouldReturnParentsInCorrectOrder() {
        List<Parent> parents = Select.using(em).from(Parent.class).list();

        Assert.assertEquals(parents.get(0).getId(), Select.using(em).from(Parent.class).orderBy(by(Parent_.id)).list().iterator().next().getId());
        Assert.assertEquals(parents.get(parents.size() - 1).getId(),
                Select.using(em).from(Parent.class).orderBy(by(Parent_.id).reversed()).list().iterator().next().getId());
    }

}
