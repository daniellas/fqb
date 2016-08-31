package com.lynx.fqb.sort;

import static com.lynx.fqb.sort.Sorts.by;
import static com.lynx.fqb.sort.Sorts.sorts;

import java.util.Collection;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.path.Paths;

public class MultipleSortTest extends MockTestBase {

    @Mock
    private SingularAttribute<Parent, Long> parentId;

    @Mock
    private SingularAttribute<Parent, String> parentName;

    @Mock
    private Path<Object> path;

    @Mock
    private Root<Parent> root;

    @Mock
    private Order order;

    @Before
    public void init() {
        super.init();
        Mockito.when(parentId.getName()).thenReturn("id");
        Mockito.when(parentName.getName()).thenReturn("name");
        Mockito.when(root.get(Mockito.anyString())).thenReturn(path);
        Mockito.when(path.get(Mockito.anyString())).thenReturn(path);
        Mockito.when(cb.asc(Mockito.any(Expression.class))).thenReturn(order);
        Mockito.when(cb.desc(Mockito.any(Expression.class))).thenReturn(order);
    }

    @Test
    public void shouldHasAppliers() {
        Assert.assertEquals(2, sorts(by(Parent_.id)).then(by(Parent_.name).reversed()).get().size());
    }

    @Test
    public void applyShouldReturnOrders() {
        Collection<Order> orders = sorts(by(parentId)).then(by(parentName).reversed()).apply(cb, root);

        Assert.assertEquals(2, orders.size());
    }

    @Test
    public void applyByAttributeShouldCallCriteriaBuilder() {
        sorts(by(parentId)).then(by(parentName).reversed()).apply(cb, root);

        Mockito.verify(cb, new Times(2)).asc(Mockito.any());
    }

    @Test
    public void applyBySelectorShouldCallCriteriaBuilder() {
        sorts(by(Paths.get(parentId))).then(by(Paths.get(parentName)).reversed()).apply(cb, root);

        Mockito.verify(cb, new Times(2)).asc(Mockito.any());
    }

}
