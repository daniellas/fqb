package com.lynx.fqb.sort;

import javax.persistence.metamodel.SingularAttribute;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.path.PathSelector;
import com.lynx.fqb.path.Paths;

public class SortsTest extends MockTestBase {

    @Mock
    private SingularAttribute<Parent, Long> attribute;

    @Before
    public void init() {
        super.init();
        Mockito.when(attribute.getName()).thenReturn("id");
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void byAttributeShouldCreateAttributeSort() {
        SortApplier sort = Sorts.by(attribute);

        Assert.assertNotNull(sort);
        Assert.assertTrue(AttributeSort.class.isAssignableFrom(sort.getClass()));
        Assert.assertEquals("id", ((AttributeSort) sort).attribute.getName());
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void bySelectorShouldCreatePathSelectorSort() {
        PathSelector<Parent, Long> pathSelector = Paths.get(attribute);
        SortApplier sort = Sorts.by(pathSelector);

        Assert.assertNotNull(sort);
        Assert.assertTrue(PathSelectorSort.class.isAssignableFrom(sort.getClass()));
        Assert.assertEquals(pathSelector, ((PathSelectorSort) sort).pathSelector);
    }

    @Test
    public void byAttributeShouldCreateMultipleSort() {
        Assert.assertEquals(1, Sorts.byAttribute(attribute).get().size());
    }

    @Test
    public void byPathShouldCreateMultipleSort() {
        Assert.assertEquals(1, Sorts.byPath(Paths.get(attribute)).get().size());
    }

}
