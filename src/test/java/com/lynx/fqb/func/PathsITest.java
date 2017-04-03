package com.lynx.fqb.func;

import static com.lynx.fqb.func.QueryBuilder.*;
import static com.lynx.fqb.func.path.Paths.*;

import javax.persistence.criteria.Root;

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

    private <T> Root<T> root(Class<T> cls) {
        return getCriteriaBuilder()
                .andThen(createCriteriaQuery(cls))
                .andThen(applyRoot(cls))
                .apply(em)
                .getRoot();
    }

}
