package com.lynx.fqb.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.path.Paths;

import lombok.Getter;

public class PredicateInterceptorsITest extends IntegrationTestBase {

    @Test
    public void shouldInterceptPredicates() {
        SpyingInterceptor spyingInterceptor = new SpyingInterceptor();

        new Select<>(spyingInterceptor)
                .fromIntercepting(Parent.class)
                .where(Predicates.of(Predicates.equal(Paths.get(Parent_.id), 1l)))
                .getResultList(em);

        Assert.assertTrue(spyingInterceptor.isExecuted());
    }

    private static class SpyingInterceptor implements PredicatesInterceptor<Parent> {

        @Getter
        private boolean executed;

        @Override
        public Predicate[] apply(CriteriaBuilder cb, Root<Parent> root, Predicate[] predicates) {
            executed = true;
            return predicates;
        }

    }
}
