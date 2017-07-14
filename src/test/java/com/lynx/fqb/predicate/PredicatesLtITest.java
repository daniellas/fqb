package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesLtITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeGt() {
        assertListResultEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(lt(SellOrder_.id, 0l))));
    }

    @Test
    public void shouldRestrictByPathEndsWith() {
        assertListResultEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(lt(get(SellOrder_.id), 0l))));
    }

    @Test
    public void shouldRestrictByExpressionEndsWith() {
        assertListResultEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(lt(
                                ofAttr(SellOrder_.id).andThen(sum(0l)), 0l))));
    }

}
