package org.artrev.workshop.junit4.stack.theory;

import org.artrev.workshop.junit4.stack.Stack;
import org.artrev.workshop.junit4.stack.actions.Action;
import org.artrev.workshop.junit4.stack.actions.ActionSequence;
import org.artrev.workshop.junit4.stack.actions.StackPopAction;
import org.artrev.workshop.junit4.stack.actions.StackPushAction;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
@RunWith(Theories.class)
public class StackTheory2 {
    @DataPoints(value = {"multiple-datapoints"})
    public static Collection<Action<Stack<String>>> actions =
            Arrays.asList(
                    ActionSequence.sequence(
                            new StackPushAction<>("foo"),
                            new StackPopAction<>()
                    ),
                    ActionSequence.sequence(
                            new StackPushAction<>("foo"),
                            new StackPushAction<>("bar"),
                            new StackPopAction<>(),
                            new StackPopAction<>()
                    ),
                    ActionSequence.sequence(
                            new StackPushAction<>("foo"),
                            new StackPushAction<>("bar"),
                            new StackPopAction<>(),
                            new StackPopAction<>(),
                            new StackPushAction<>("baz"),
                            new StackPopAction<>()
                    )
            );

    @Theory
    public void equalNumberOfPushAndPopCallsWillResultWithTheInitialStack(@FromDataPoints("multiple-datapoints") Action<Stack<String>> action) {
        // given:
        final Stack<String> tested = new Stack.NonEmpty<>(
                new Stack.Empty<>(),
                "42"
        );
        final int expectedDepth = tested.getDepth();
        final String expectedTop = tested.peek();

        // when:
        final Stack<String> result = action.run(tested);

        // then:
        assertEquals(expectedDepth, result.getDepth());
        assertEquals(expectedTop, result.peek());
    }
}
