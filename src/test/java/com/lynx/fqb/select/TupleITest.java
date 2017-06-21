package com.lynx.fqb.select;

import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;

public class TupleITest extends IntegrationTestBase {

    @Test
    public void shouldAccessTupleResultByIndex() {
        List<Tuple> resultList = Select.tupleFrom(Parent.class).with(Selections.of(
                Selections.attr(Parent_.id),
                Selections.attr(Parent_.dateCreate))).getResultList(em);

        Assert.assertNotNull(resultList.get(0).get(0, Long.class));
        Assert.assertNotNull(resultList.get(0).get(1, Date.class));
    }

}
