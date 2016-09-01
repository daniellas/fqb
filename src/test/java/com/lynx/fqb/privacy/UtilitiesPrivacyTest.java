package com.lynx.fqb.privacy;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import com.lynx.fqb.ConstructorPrivacyTestBase;

public class UtilitiesPrivacyTest extends ConstructorPrivacyTestBase {

    @Parameters
    public static Collection<Class<?>> parameters() {
        return Arrays.asList(TestedSorts.class, TestedPaths.class);
    }
}
