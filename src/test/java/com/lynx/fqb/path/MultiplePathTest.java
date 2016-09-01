package com.lynx.fqb.path;

import java.util.List;

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

public class MultiplePathTest extends MockTestBase {

    @Mock
    private SingularAttribute<Child, Parent> parent;

    @Mock
    private SingularAttribute<Parent, Long> parentId;

    @Mock
    private SingularAttribute<Child, Long> childId;

    @Mock
    private Root<Child> root;

    @Mock
    private Path<Object> path;

    @Before
    public void init() {
        super.init();
        Mockito.when(parent.getName()).thenReturn("parent");
        Mockito.when(parentId.getName()).thenReturn("id");
        Mockito.when(childId.getName()).thenReturn("id");
        Mockito.when(root.get(Mockito.anyString())).thenReturn(path);
    }

    @Test
    public void shouldCreatePaths() {
        List<Path<?>> paths = Paths.of(parent).and(parentId).apply(root);

        Assert.assertEquals(2, paths.size());
    }

    @Test
    public void shouldCreatePathsByMixed() {
        List<Path<?>> paths = Paths.of(parent).and(Paths.get(childId)).apply(root);

        Assert.assertEquals(2, paths.size());
    }

    @Test
    public void shouldCreatePathsByPath() {
        List<Path<?>> paths = Paths.of(Paths.get(parent).get(parentId)).apply(root);

        Assert.assertEquals(1, paths.size());
    }

    @Test
    public void shouldCreatePathsByPathAndPath() {
        List<Path<?>> paths = Paths.of(Paths.get(parent).get(parentId)).and(childId).apply(root);

        Assert.assertEquals(2, paths.size());
    }

}
