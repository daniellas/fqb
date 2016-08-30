package com.lynx.fqb.privacy;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import com.lynx.fqb.ConstructorPrivacyTestBase;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.sort.Sorts;

public class UtilitiesPrivacyTest extends ConstructorPrivacyTestBase {

    @Parameters
    public static Collection<Class<?>> parameters() {
        return Arrays.asList(Sorts.class, Paths.class);
    }
}
