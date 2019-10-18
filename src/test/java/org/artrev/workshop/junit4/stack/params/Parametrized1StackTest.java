package org.artrev.workshop.junit4.stack.params;

import org.artrev.workshop.junit4.stack.Stack;
import org.artrev.workshop.junit4.stack.actions.Actions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

@RunWith(Parameterized.class)
public class Parametrized1StackTest {
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

    @Parameterized.Parameter
    public int numberOfPushCalls;

    // Do not mess the order of the parameters
    // if you do not want to get an ugly InitializerError
    @Parameterized.Parameter(1)
    public int numberOfPopCalls;

    @Parameterized.Parameter(2)
    public int expectedDepth;

    /*
      This tests tries to form a property of the stack that
      pushing `x` elements on the stack and then popping `y` elements
      will result with a `z` depth.

      We cannot say that it holds for any order of operations
      but this is a good start.
     */
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

    /*
      It is possible to reuse the parameters for another test
      the only necessity is using assumptions about the values
      to control what is passed to the test.

      Readability of the results is suffering.
     */
    @Test
    public void equalAmountOfPushAndPopWillResultInAnEmptyStack() {
        assumeTrue(numberOfPushCalls == numberOfPopCalls);

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
        final boolean result = afterPop.isEmpty();

        // then:
        assertTrue(result);
    }
}