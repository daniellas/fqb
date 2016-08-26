package com.lynx.fqb;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import com.lynx.fqb.equality.EqualityHashTestBase;
import com.lynx.fqb.equality.EqualityPair;

public class PageRequestEqualityTest extends EqualityHashTestBase<PageRequest> {

    @Parameters
    public static Collection<EqualityPair<PageRequest>> parameters() {
        PageRequest me = PageRequest.of(0, 1);

        return Arrays.asList(
                // Same
                EqualityPair.equalPair(() -> PageRequest.of(0, 1), () -> PageRequest.of(0, 1)),
                // that==null
                EqualityPair.inequalPair(() -> PageRequest.of(0, 1), () -> null),
                // that==""
                EqualityPair.inequalPair(() -> PageRequest.of(0, 1), () -> ""),
                // Same reference)
                EqualityPair.equalPair(() -> me, () -> me),
                // !=pageSize
                EqualityPair.inequalPair(() -> PageRequest.of(0, 1), () -> PageRequest.of(0, 2)),
                // !=pageNumber
                EqualityPair.inequalPair(() -> PageRequest.of(0, 1), () -> PageRequest.of(1, 1)));
    }

}
