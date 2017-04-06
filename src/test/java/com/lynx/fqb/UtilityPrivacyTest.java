package com.lynx.fqb;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import com.lynx.fqb.util.Combinators;
import com.lynx.fqb.util.QueryBuilder;

public class UtilityPrivacyTest extends ConstructorPrivacyTestBase {

    @Parameters
    public static Collection<Class<?>> parameters() {
        return Arrays.asList(QueryBuilder.class, Combinators.class);
    }
}
