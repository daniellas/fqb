package com.lynx.fqb.performance;

import java.util.stream.IntStream;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Parent;

import lombok.extern.slf4j.Slf4j;

// FIXME There are errors on parallel selects execution
@Slf4j
public class PerformanceIPTest extends IntegrationTestBase {

    private static final int COUNT = 10000;

    @Test
    public void simpleSelectSerialPerformanceTest() {
        TimePrinter.run(() -> {
            IntStream.range(0, COUNT).forEach(i -> {
                Select.from(Parent.class).getResultList(em);
            });
        }, "Simple select serial");
    }

    @Test
    public void simpleSelectParallelPerformanceTest() {
        TimePrinter.run(() -> {
            IntStream.range(0, COUNT).parallel().forEach(i -> {
                    Select.from(Parent.class).getResultList(em);
            });
        }, "Simple select parallel");
    }

    private static class TimePrinter {
        public static void run(Runnable task, String name) {
            int errors = 0;
            long start = System.currentTimeMillis();

            try {
                task.run();
            } catch (RuntimeException e) {
                errors++;
            }
            long end = System.currentTimeMillis();
            log.info("{}: Executed {} queries in {} miliseconds, errors: {}", name, COUNT, end - start, errors);
        }
    }
}
