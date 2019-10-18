package org.artrev.workshop.junit4.stack;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

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
        final Stack<String> tested = new Stack.NonEmpty<>(new Stack.Empty<>(), "foo");
        // when:
        final Stack<String> actual = tested.pop();
        // then:
        assertTrue(actual.isEmpty());
    }

    /*
      Handling exceptions with `expected` argument of the @Test annotation:
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldThrowAnExceptionAfterPeekForEmptyStack() {
        // given:
        final Stack<Integer> tested = new Stack.Empty<>();
        // when:
        tested.peek();
    }

    /*
      Handling exceptions with JUnit Rule (Expected Exception):
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowAnExceptionAfterPopForEmptyStack() {
        // given:
        final Stack<Integer> tested = new Stack.Empty<>();
        // when:
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage(CoreMatchers.containsString("pop"));
        tested.pop();
    }
}
