package com.lynx.fqb.select;

import org.junit.Ignore;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.sort.Sorts;

@Ignore
public class SelectStructureTest extends MockTestBase {
    public void dummy() {
        Select.using(em).distinct().from(Parent.class).where().orderBy(Sorts.attribute(Parent_.id));
    }
}
