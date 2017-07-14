package com.lynx.fqb.predicate;

import static com.lynx.fqb.expression.Expressions.*;
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;

public class PredicatesNullITest extends IntegrationTestBase {

    @Test
    public void shouldRestrictByAttributeIsNull() {
        assertListResultEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(isNull(SellOrder_.id))));
    }

    @Test
    public void shouldRestrictByPathIsNull() {
        assertListResultEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(isNull(get(SellOrder_.id)))));
    }

    @Test
    public void shouldRestrictByExpressionIsNull() {
        assertListResultEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(isNull(ofPath(get(SellOrder_.id)).andThen(sum(0l))))));
    }

    @Test
    public void shouldRestrictByAttributeIsNotNull() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(isNotNull(SellOrder_.id))));
    }

    @Test
    public void shouldRestrictByPathIsNotNull() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(isNotNull(get(SellOrder_.id)))));
    }

    @Test
    public void shouldRestrictByExpressionIsNotNull() {
        assertListResultNotEmpty()
                .accept(Select.from(SellOrder.class)
                        .where(of(isNotNull(ofPath(get(SellOrder_.id)).andThen(sum(0l))))));
    }

}
