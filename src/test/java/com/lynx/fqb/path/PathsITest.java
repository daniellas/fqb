package com.lynx.fqb.path;

import static com.lynx.fqb.path.Paths.*;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;

public class PathsITest extends IntegrationTestBase {

    @Test
    public void shouldGetSinglePath() {
        Assert.assertNotNull(get(Parent_.id).apply(root(Parent.class)));
    }

    @Test
    public void shouldGetNestedPath() {
        Assert.assertNotNull(get(Child_.parent).andThen(get(Parent_.name)).apply(root(Child.class)));
    }

    // @Test
    // public void shouldGetNestedPathByGet() {
    // Assert.assertNotNull(get(Child_.parent).get(Parent_.name).apply(root(Child.class)));
    // }

    // @Test
    // public void shouldGetListAttribute() {
    // Assert.assertNotNull(getList(Parent_.children).apply(root(Parent.class)));
    // }

    @Test
    public void shouldGetInheritedAttribute() {
        Assert.assertNotNull(get(Parent_.dateCreate).apply(root(Parent.class)));
    }

    @Test
    public void shouldGetInheritedNestedAttribute() {
        Assert.assertNotNull(get(Child_.parent).andThen(get(Parent_.dateCreate)).apply(root(Child.class)));
    }

}
