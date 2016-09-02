package com.lynx.fqb.select;

import static com.lynx.fqb.path.Paths.get;
import static com.lynx.fqb.sort.Sorts.by;
import static com.lynx.fqb.sort.Sorts.sorts;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;

public class SortedSelectITest extends IntegrationTestBase {

    private Object value = null;

    @Test
    public void shouldSortBySort() {
        Select.using(em).from(Parent.class).orderBy(sorts(by(Parent_.id).reversed())).list();
    }

    @Test
    public void shouldSortByMultipleSort() {
        Select.using(em).from(Parent.class).orderBy(sorts(by(Parent_.id)).then(by(Parent_.name))).list();
    }

    @Test
    public void shouldSortByMultipleSortWithCrossJoin() {
        Select.using(em).from(Child.class).orderBy(sorts(by(Child_.id)).then(by(get(Child_.parent).get(Parent_.name)))).list();
    }

    @Test
    public void shouldSortByMultipleSortSupplier() {
        Select.using(em).from(Parent.class).orderBy(() -> {
            return Optional.ofNullable(value)
                    .map(v -> sorts(by(Parent_.id)).then(by(Parent_.name)))
                    .orElse(sorts(by(Parent_.id)));
        }).list();
    }

    @Test
    public void shouldSortByPathSelector() {
        Select.using(em).from(Child.class).orderBy(sorts(by(get(Child_.parent).get(Parent_.id)))).list();
    }

    @Test
    public void shouldSortByPathSelectorReversed() {
        Select.using(em).from(Child.class).orderBy(sorts(by(get(Child_.parent).get(Parent_.id)).reversed())).list();
    }

    @Test
    public void shouldReturnParentsInCorrectOrder() {
        List<Parent> parents = Select.using(em).from(Parent.class).list();

        Assert.assertEquals(parents.get(0).getId(),
                Select.using(em).from(Parent.class).orderBy(sorts(by(Parent_.id))).list().iterator().next().getId());
        Assert.assertEquals(parents.get(parents.size() - 1).getId(),
                Select.using(em).from(Parent.class).orderBy(sorts(by(Parent_.id).reversed())).list().iterator().next().getId());
    }

}
