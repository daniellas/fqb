package com.lynx.fqb.cb;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;

/**
 * This is just the CriteriaBuilder sand box
 * 
 * @author daniel.las
 *
 */
public class CriteriaBuilderITest extends IntegrationTestBase {

    @Test
    public void shouldReturnObjectArrayList() {
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Parent> from = query.from(Parent.class);

        query.select(cb.construct(Object[].class, new Selection[] { from.get(Parent_.id), from.get(Parent_.name) }));

        List<Object[]> resultList = em.createQuery(query).getResultList();

        Assert.assertTrue(Object[].class.isAssignableFrom(resultList.iterator().next().getClass()));
    }

    @Test
    public void shouldReturnResList() {
        CriteriaQuery<Res> query = cb.createQuery(Res.class);
        Root<Parent> from = query.from(Parent.class);

        query.select(cb.construct(Res.class, new Selection[] { from.get(Parent_.id) }));

        List<Res> resultList = em.createQuery(query).getResultList();
        Assert.assertTrue(Res.class.isAssignableFrom(resultList.iterator().next().getClass()));
    }

    protected static class Res {
        private Long id;

        public Res(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

}
