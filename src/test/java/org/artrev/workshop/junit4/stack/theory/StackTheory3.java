package org.artrev.workshop.junit4.stack.theory;

import org.artrev.workshop.junit4.stack.Stack;
import org.artrev.workshop.junit4.stack.actions.Action;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class StackTheory3 {
    @Theory
    public void equalNumberOfPushAndPopCallsWillResultWithTheInitialStack(@InverseActionSequences Action<Stack<String>> action) {
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
