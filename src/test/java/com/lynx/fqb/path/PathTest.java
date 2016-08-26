package com.lynx.fqb.path;

import javax.persistence.criteria.Path;

import org.junit.Test;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.Parent_;

public class PathTest extends MockTestBase {

    @Test
    public void pathShouldBeCorrectlyResolved() {
        Path<String> apply = Paths.get(Child_.parent).thenGet(Parent_.name).apply(null);
    }
}
