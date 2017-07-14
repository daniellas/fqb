package com.lynx.fqb;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import com.lynx.fqb.expression.Expressions;
import com.lynx.fqb.group.Groupings;
import com.lynx.fqb.join.Joins;
import com.lynx.fqb.order.Orders;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.predicate.Predicates;
import com.lynx.fqb.selection.Selections;
import com.lynx.fqb.util.Combinators;
import com.lynx.fqb.util.QueryBuilder;

public class UtilityPrivacyTest extends ConstructorPrivacyTestBase {

    @Parameters
    public static Collection<Class<?>> parameters() {
        return Arrays.asList(Select.class, QueryBuilder.class, Combinators.class, Find.class, Merge.class,
                Persist.class, Remove.class, Predicates.class, Paths.class, Expressions.class, Joins.class,
                Orders.class, Selections.class, Groupings.class);
    }
}
