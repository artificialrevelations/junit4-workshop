package org.artrev.workshop.junit4.stack.theory;

import org.artrev.workshop.junit4.stack.Stack;
import org.artrev.workshop.junit4.stack.actions.Action;
import org.artrev.workshop.junit4.stack.actions.ActionSequence;
import org.artrev.workshop.junit4.stack.actions.StackPopAction;
import org.artrev.workshop.junit4.stack.actions.StackPushAction;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
@RunWith(Theories.class)
public class StackTheory1 {
    /*
      Regardless of the number of push and pop calls on the stack and their
      order (excluding invalid pops e.g. on empty stack) stack size after all
      operations are applied should be the same like before the operations.
     */
    @DataPoint(value = {"push-and-pops", "p-a-p"})
    public static Action<Stack<String>> actions = ActionSequence.sequence(
            new StackPushAction<>("foo"),
            new StackPushAction<>("bar"),
            new StackPopAction<>(),
            new StackPopAction<>(),
            new StackPushAction<>("baz"),
            new StackPopAction<>()
    );

    @Theory
    public void equalNumberOfPushAndPopCallsWillResultWithTheInitialStack(@FromDataPoints("push-and-pops") Action<Stack<String>> action) {
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
