package com.lynx.fqb;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import com.lynx.fqb.combinator.Combinators;
import com.lynx.fqb.util.QueryBuilder;

public class UtilityPrivacyTest extends ConstructorPrivacyTestBase {

    @Parameters
    public static Collection<Class<?>> parameters() {
        return Arrays.asList(Select.class, QueryBuilder.class, Combinators.class, Persist.class, Merge.class, Get.class);
    }
}
