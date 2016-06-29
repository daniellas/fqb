package com.lynx.fqb.test;

import com.lynx.fqb.Paths;
import com.lynx.fqb.Sorts;
import com.lynx.fqb.api.PathSelector;
import com.lynx.fqb.api.SortApplier;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.select.SelectQuery;

import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleSelectTest {

    private static EntityManagerFactory emf;

    private EntityManager em;

    private CriteriaBuilder cb;

    private static boolean initialized;

    @BeforeClass
    public static void initOnce() {
        emf = Persistence.createEntityManagerFactory("test");
    }

    @Before
    public void init() {
        em = emf.createEntityManager();
        cb = em.getCriteriaBuilder();
        if (!initialized) {
            initialized = true;

            Parent parent = new Parent();

            parent.addChild(new Child());

            em.persist(parent);
        }
    }

    @After
    public void clean() {
        em.close();
    }

    @Test
    public void criteriaBuilderTest() {
        CriteriaQuery<Child> query = cb.createQuery(Child.class);
        Root<Child> from = query.from(Child.class);

        // query.where(cb.equal(from.get(Parent_.id), 1));
        query.orderBy(cb.asc(from.get(Child_.parent).get(Parent_.id)));

        from.get(Child_.parent).get(Parent_.id);
        TypedQuery<Child> result = em.createQuery(query);

        result.getResultList();
    }

    @Test
    public void parentSelectShouldReturnResultList() {

        new SelectQuery<Parent, Parent>(cb)
                .select(Parent.class)
                .from(Parent.class)
                .orderBy(Sorts.by(Parent_.id))
                .getResultList(em);
    }

    @Test
    public void childSelectShouldReturnResultList() {
        Function<Path<Parent>, Path<Long>> thenGet = Paths.get(Child_.parent).thenGet(Parent_.id);
        BiFunction<CriteriaBuilder, Path<Parent>, Order> reversed = Sorts.by(thenGet).reversed();

        new SelectQuery<Child, Child>(cb)
                .select(Child.class)
                .from(Child.class)
                .getResultList(em);
    }

}
