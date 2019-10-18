package org.artrev.workshop.junit4.stack.params;

import org.artrev.workshop.junit4.stack.Stack;
import org.artrev.workshop.junit4.stack.actions.Action;
import org.artrev.workshop.junit4.stack.actions.Actions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class Parametrized2StackTest {
    @Parameterized.Parameters(
            name = "#{index} pushing {0} elements, popping {1} elements, depth is {2}"
    )
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // {push, pop, depth}
                {0, 0, 0},
                {3, 0, 3},
                {3, 2, 1},
                {2, 2, 0}
        });
    }

    private final int numberOfPushCalls;
    private final int numberOfPopCalls;
    private final int expectedDepth;

    public Parametrized2StackTest(final int numberOfPushCalls,
                                  final int numberOfPopCalls,
                                  final int expectedDepth) {
        this.numberOfPushCalls = numberOfPushCalls;
        this.numberOfPopCalls = numberOfPopCalls;
        this.expectedDepth = expectedDepth;
    }

    @Test
    public void testExpectedDepth() {
        // given:
        final Stack<String> afterPush = Actions.perform(
                new Stack.Empty<>(),
                numberOfPushCalls,
                stack -> stack.push("foo")
        );

        final Stack<String> afterPop = Actions.perform(
                afterPush,
                numberOfPopCalls,
                Stack::pop
        );

        // when:
        final int actualDepth = afterPop.getDepth();

        // then:
        assertEquals(expectedDepth, actualDepth);
    }
}
