package com.lynx.fqb.path;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.Parent_;

public class PathITest extends IntegrationTestBase {

    @Test
    public void pathsTest() {
        CriteriaQuery<Child> query = cb.createQuery(Child.class);
        Root<Child> from = query.from(Child.class);
        Path<?> path = Paths.get(Child_.parent).get(Parent_.name).apply(from);

        query.orderBy(cb.asc(path));

        em.createQuery(query).getResultList();

    }

}
