package com.lynx.fqb.find;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.Find;
import com.lynx.fqb.Find.InterceptingFind;
import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.intercept.PostInterceptor;

public class FindITest extends IntegrationTestBase {

    @Test
    public void shouldGetById() {
        Optional<Parent> entity = Find.entity(Parent.class).byId(1l).apply(em);

        Assert.assertEquals(new Long(1), entity.get().getId());
    }

    @Test
    public void shouldReturnEmpty() {
        Optional<Parent> entity = Find.entity(Parent.class).byId(-11l).apply(em);

        Assert.assertFalse(entity.isPresent());
    }

    @Test
    public void shouldInterceptAndReturnEntity() {
        Optional<Parent> result = new InterceptingFind<>(new OnlyFirstFindInterceptor()).entity(Parent.class).byId(1l).apply(em);

        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void shouldInterceptAndReturnEmpty() {
        Long id = 3l;

        Assert.assertTrue(Find.entity(Parent.class).byId(id).apply(em).isPresent());

        Optional<Parent> result = new InterceptingFind<>(new OnlyFirstFindInterceptor()).entity(Parent.class).byId(id).apply(em);

        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void shouldIdentityInterceptOnExistingResult() {
        Optional<Parent> result = new InterceptingFind<Parent>(PostInterceptor.noOp()).entity(Parent.class).byId(1l).apply(em);

        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void shouldIdentityInterceptOnEmptyResult() {
        Optional<Parent> result = new InterceptingFind<Parent>(PostInterceptor.noOp()).entity(Parent.class).byId(-1l).apply(em);

        Assert.assertFalse(result.isPresent());
    }

    private static class OnlyFirstFindInterceptor implements PostInterceptor<Parent> {

        @Override
        public Optional<Parent> apply(Parent entity) {
            if (entity.getId().equals(1l)) {
                return Optional.of(entity);
            }

            return Optional.empty();
        }

    }
}
