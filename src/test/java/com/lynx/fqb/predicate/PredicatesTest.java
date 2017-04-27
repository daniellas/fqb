package com.lynx.fqb.predicate;

import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.Parent_;

public class PredicatesTest {

    @Test
    public void shouldCombinePredicates() {
        of(and(
                equal(get(Child_.id), 1l),
                equal(get(Child_.id), 2l),
                equal(get(Child_.parent).get(Parent_.name), ""),
                or(
                        equal(get(Child_.name), ""),
                        equal(get(Child_.name), "XX"))));
    }
}
