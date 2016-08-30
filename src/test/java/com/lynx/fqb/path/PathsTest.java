package com.lynx.fqb.path;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Parent;

public class PathsTest extends MockTestBase {

    @Mock
    private SingularAttribute<Child, Parent> parent;

    @Mock
    private SingularAttribute<Parent, Long> parentId;

    @Mock
    private Root<Child> root;

    @Mock
    private Path<Object> path;

    @Before
    public void init() {
        super.init();
        Mockito.when(parent.getName()).thenReturn("parent");
        Mockito.when(parentId.getName()).thenReturn("id");
    }

    @Test
    public void shouldCreatePathNode() {
        PathSelector<Child, Parent> pathSelector = Paths.get(parent);

        Assert.assertNotNull(pathSelector);
        Assert.assertTrue(PathNode.class.isAssignableFrom(pathSelector.getClass()));
        Assert.assertEquals(1, pathSelector.get().size());
    }

    @Test
    public void shouldCreateAttributePath() {
        PathSelector<Parent, Long> pathSelector = Paths.get(parent).get(parentId);

        Assert.assertEquals(2, pathSelector.get().size());
    }

    @Test
    public void pathShouldBeResolved() {
        Mockito.when(root.get(Mockito.anyString())).thenReturn(path);
        Mockito.when(path.get(Mockito.anyString())).thenReturn(path);
        Assert.assertEquals(path, Paths.get(parent).get(parentId).apply(root));
    }
}
