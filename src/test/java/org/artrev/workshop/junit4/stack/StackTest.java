package org.artrev.workshop.junit4.stack;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StackTest {
    @Test
    public void stackIsNotEmptyAfterPushingOneElement() {
        // given:
        final Stack<String> tested = new Stack.Empty<>();
        // when:
        final Stack<String> actual = tested.push("foo");
        // then:
        assertFalse(actual.isEmpty());
    }

    @Test
    public void stackIsEmptyAfterPoppingLastElement() {
        // given:
        final Stack<String> tested = new Stack.NonEmpty<>(
                new Stack.Empty<>(),
                "foo"
        );
        // when:
        final Stack<String> actual = tested.pop();
        // then:
        assertTrue(actual.isEmpty());
    }
}
