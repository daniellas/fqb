package com.lynx.fqb.sort;

import javax.persistence.metamodel.SingularAttribute;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Parent;

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
}
