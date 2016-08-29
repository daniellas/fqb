package com.lynx.fqb.select;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.sort.SortApplier;
import com.lynx.fqb.sort.Sorts;

public class OrderedSelectITest extends IntegrationTestBase {

    @Test
    public void shouldSortBySort() {
        Stream.of(Sorts.by(Parent_.id), Sorts.by(Parent_.name));
        Select.using(em).from(Parent.class).orderBy(Sorts.by(Parent_.id).reversed()).list();
    }

    @Test
    public void shouldSortBySortsStream() {
        Select.using(em).from(Parent.class)
                .orderBy(Stream.of(Sorts.by(Parent_.id), Sorts.by(Parent_.name)))
                .list();
    }

    @Test
    public void shouldSortBySortsList() {
        Select.using(em).from(Parent.class)
                .orderBy(Arrays.asList(Sorts.by(Parent_.id), Sorts.by(Parent_.name)))
                .list();
    }

    @Test
    public void shouldSortByPathSelector() {
        Select.using(em).from(Child.class).orderBy(Sorts.by(Paths.get(Child_.parent).get(Parent_.id))).list();
    }

    @Test
    public void shouldReturnParentsInCorrectOrder() {
        List<Parent> parents = Select.using(em).from(Parent.class).list();

        Assert.assertEquals(parents.get(0).getId(), Select.using(em).from(Parent.class).orderBy(Sorts.by(Parent_.id)).list().iterator().next().getId());
        Assert.assertEquals(parents.get(parents.size() - 1).getId(),
                Select.using(em).from(Parent.class).orderBy(Sorts.by(Parent_.id).reversed()).list().iterator().next().getId());
    }

}
