package com.lynx.fqb;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public abstract class ConstructorPrivacyTestBase {

    @Parameter
    public Class<?> testedClass;

    @Test
    public void testPrivacy() throws NoSuchMethodException, SecurityException {
        Constructor<?> constructor = testedClass.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
    }

    @Test
    public void shouldSuccessOnReflectionInstantiation() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Constructor<?> constructor = testedClass.getDeclaredConstructor();

        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
