package com.lynx.fqb.find;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.Find;
import com.lynx.fqb.Find.InterceptingFind;
import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.intercept.EntityInterceptor;

public class FindITest extends IntegrationTestBase {

    @Test
    public void shouldGetById() {
        Optional<SellOrder> entity = Find.entity(SellOrder.class).byId(1l).apply(em);

        Assert.assertEquals(new Long(1), entity.get().getId());
    }

    @Test
    public void shouldReturnEmpty() {
        Optional<SellOrder> entity = Find.entity(SellOrder.class).byId(-11l).apply(em);

        Assert.assertFalse(entity.isPresent());
    }

    @Test
    public void shouldInterceptAndReturnEntity() {
        Optional<SellOrder> result = new InterceptingFind<>(new OnlyFirstFindInterceptor()).entity(SellOrder.class).byId(1l).apply(em);

        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void shouldInterceptAndReturnEmpty() {
        Long id = 4l;

        Assert.assertTrue(Find.entity(SellOrder.class).byId(id).apply(em).isPresent());

        Optional<SellOrder> result = new InterceptingFind<>(new OnlyFirstFindInterceptor()).entity(SellOrder.class).byId(id).apply(em);

        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void shouldIdentityInterceptOnExistingResult() {
        Optional<SellOrder> result = new InterceptingFind<SellOrder>(EntityInterceptor.noOp()).entity(SellOrder.class).byId(1l).apply(em);

        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void shouldIdentityInterceptOnEmptyResult() {
        Optional<SellOrder> result = new InterceptingFind<SellOrder>(EntityInterceptor.noOp()).entity(SellOrder.class).byId(-1l).apply(em);

        Assert.assertFalse(result.isPresent());
    }

    private static class OnlyFirstFindInterceptor implements EntityInterceptor<SellOrder> {

        @Override
        public Optional<SellOrder> apply(EntityManager em, SellOrder entity) {
            if (entity.getId().equals(1l)) {
                return Optional.of(entity);
            }

            return Optional.empty();
        }

    }
}
