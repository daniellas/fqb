package com.lynx.fqb.util;

import static com.lynx.fqb.util.QueryBuilder.*;

import java.util.Arrays;

import javax.persistence.Tuple;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.path.Paths;

public class QueryBuilderITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntity() {
        getCriteriaBuilder()
                .andThen(createCriteriaQuery(Parent.class))
                .andThen(applyRoot(Parent.class))
                .andThen(applyRootSelection())
                .andThen(createTypedQuery(em))
                .apply(em)
                .getResultList();
    }

    @Test
    public void shouldSelectCustomResult() {
        getCriteriaBuilder()
                .andThen(createCriteriaQuery(CustomResult.class))
                .andThen(applyRoot(Parent.class))
                .andThen(applySelection(cb, (cb, root) -> {
                    return Arrays.asList(Paths.get(Parent_.id), Paths.get(Parent_.name)).stream()
                            .map(p -> {
                                return p.apply(root);
                            })
                            .toArray(Selection<?>[]::new);
                }))
                .andThen(createTypedQuery(em))
                .apply(em)
                .getResultList();
    }

    @Test
    public void shouldSelectCustomResultFromNestedPaths() {
        getCriteriaBuilder()
                .andThen(createCriteriaQuery(CustomResult.class))
                .andThen(applyRoot(Child.class))
                .andThen(applySelection(cb, (cb, root) -> {
                    return Arrays.asList(Paths.get(Child_.id), Paths.get(Child_.parent).andThen(Paths.get(Parent_.name))).stream()
                            .map(p -> {
                                return p.apply(root);
                            })
                            .toArray(Selection<?>[]::new);
                }))
                .andThen(createTypedQuery(em))
                .apply(em)
                .getResultList();
    }

    @Test
    public void shouldSelectTuple() {
        getCriteriaBuilder()
                .andThen(createCriteriaQuery(Tuple.class))
                .andThen(applyRoot(Parent.class))
                .andThen(applySelection(cb, (cb, root) -> {
                    return Arrays.asList(Paths.get(Parent_.id), Paths.get(Parent_.name)).stream()
                            .map(p -> {
                                return p.apply(root);
                            })
                            .toArray(Selection<?>[]::new);
                }))
                .andThen(createTypedQuery(em))
                .apply(em)
                .getResultList();
    }

    @Test
    public void shouldSelectWithRestriction() {
        getCriteriaBuilder()
                .andThen(createCriteriaQuery(Parent.class))
                .andThen(applyRoot(Parent.class))
                .andThen(applyRootSelection())
                .andThen(applyRestriction(cb, (cb, root) -> {
                    return Arrays.asList(cb.equal(Paths.get(Parent_.id).apply(root), 1l)).stream().toArray(Predicate[]::new);
                }))
                .andThen(createTypedQuery(em))
                .apply(em)
                .getResultList();
    }
}
