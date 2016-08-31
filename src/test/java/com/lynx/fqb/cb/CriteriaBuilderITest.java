package com.lynx.fqb.cb;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Ignore;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;

/**
 * This is just the CriteriaBuilder sanbox
 * 
 * @author daniel.las
 *
 */
@Ignore
public class CriteriaBuilderITest extends IntegrationTestBase {

    @SuppressWarnings("unused")
    @Test
    public void shouldApplyRestrictions() {
        CriteriaQuery<Child> query = cb.createQuery(Child.class);
        Root<Child> from = query.from(Child.class);

        query.where(cb.and(cb.equal(from.get(Child_.id), 1)));

        Predicate equal = cb.equal(from.get(Child_.id), 1);
        Expression<String> substring = cb.substring(from.get(Child_.name), 1);

        query.distinct(true);

        em.createQuery(query).getResultList();

    }

}
