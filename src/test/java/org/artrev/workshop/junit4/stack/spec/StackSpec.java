package org.artrev.workshop.junit4.stack.spec;

import org.artrev.workshop.junit4.stack.Stack;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class StackSpec {
    public static class EmptyStack {
        @Test
        public void afterCreationIsEmpty() {
            // given:
            final Stack<String> tested = new Stack.Empty<>();
            // when:
            final boolean result = tested.isEmpty();
            // then:
            assertTrue(result);
        }

        @Test
        public void afterCreationHasDepthOfZero() {
            // given:
            final Stack<String> tested = new Stack.Empty<>();
            // when:
            final int actualDepth = tested.getDepth();
            // then:
            assertEquals(0, actualDepth);
        }

        @Test(expected = IllegalStateException.class)
        public void throwsAnExceptionAfterPopIsCalled() {
            // given:
            final Stack<String> tested = new Stack.Empty<>();
            // when:
            tested.pop();
        }

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        @Test
        public void throwsAnExceptionAfterPeekIsCalled() {
            // given:
            final Stack<String> tested = new Stack.Empty<>();
            // when:
            thrown.expect(NoSuchElementException.class);
            thrown.expectMessage(containsString("does not have any elements"));
            tested.peek();
        }

        @Test
        public void isNotEmptyAfterElementIsPushed() {
            // given:
            final Stack<String> tested = new Stack.Empty<>();
            // when:
            final Stack<String> result = tested.push("push");
            // then:
            assertFalse(result.isEmpty());
        }

        @Test
        public void hasDepthOfOneAfterElementIsPushed() {
            // given:
            final Stack<String> tested = new Stack.Empty<>();
            // when:
            final Stack<String> result = tested.push("depth");
            // then:
            assertEquals(1, result.getDepth());
        }

        @Test
        public void topElementIsEqualToTheElementPushed() {
            // given:
            final String expected = "push-peek";
            final Stack<String> tested = new Stack.Empty<>();
            // when:
            final String actual = tested.push(expected).peek();
            // then:
            assertEquals(expected, actual);
        }
    }

    public static class SingleElementStack {
        @Test
        public void afterCreationIsNotEmpty() {
            // given:
            final Stack<Integer> tested = new Stack.NonEmpty<>(
                    new Stack.Empty<>(),
                    42
            );
            // when:
            final boolean result = tested.isEmpty();
            // then:
            assertFalse(result);
        }

        @Test
        public void afterCreationHasDepthOfOne() {
            // given:
            final Stack<Integer> tested = new Stack.NonEmpty<>(
                    new Stack.Empty<>(),
                    42
            );
            // when:
            final int actualDepth = tested.getDepth();
            // then:
            assertEquals(1, actualDepth);
        }

        @Test
        public void isEmptyAfterPopIsCalled() {
            // given:
            final Stack<Integer> tested = new Stack.NonEmpty<>(
                    new Stack.Empty<>(),
                    42
            );
            // when:
            final Stack<Integer> result = tested.pop();
            // then:
            assertTrue(result.isEmpty());
        }

        @Test
        public void hasDepthOfZeroAfterPopIsCalled() {
            // given:
            final Stack<Integer> tested = new Stack.NonEmpty<>(
                    new Stack.Empty<>(),
                    42
            );
            // when:
            final int actualDepth = tested.pop().getDepth();
            // then:
            assertEquals(0, actualDepth);
        }

        @Test
        public void returnsItsOnlyValueAfterPeekIsCalled() {
            // given:
            final int expected = 42;
            final Stack<Integer> tested = new Stack.NonEmpty<>(
                    new Stack.Empty<>(),
                    expected
            );
            // when:
            final int actual = tested.peek();
            // then:
            assertEquals(expected, actual);
        }

        @Test
        public void hasDepthOfTwoAfterPushIsCalled() {
            // given:
            final Stack<Integer> tested = new Stack.NonEmpty<>(
                    new Stack.Empty<>(),
                    42
            );
            // when:
            final int actualDepth = tested.push(24).getDepth();
            // then:
            assertEquals(2, actualDepth);
        }

        @Test
        public void hasANewTopElementAfterPushIsCalled() {
            // given:
            final int expected = 24;
            final Stack<Integer> tested = new Stack.NonEmpty<>(
                    new Stack.Empty<>(),
                    42
            );
            // when:
            final int actual = tested.push(expected).peek();
            // then:
            assertEquals(expected, actual);
        }
    }

    public static class NonEmptyStack {
        @Test
        public void afterCreationIsNotEmpty() {
            // given:
            final Stack<Long> tested =
                    (new Stack.Empty<Long>())
                            .push(1L)
                            .push(2L)
                            .push(3L);
            // when:
            final boolean result = tested.isEmpty();
            // then:
            assertFalse(result);
        }

        @Test
        public void afterCreationHasNonZeroDepth() {
            // given:
            final Stack<Long> tested =
                    (new Stack.Empty<Long>())
                            .push(1L)
                            .push(2L)
                            .push(3L);
            // when:
            final int actualDepth = tested.getDepth();
            // then:
            assertEquals(3, actualDepth);
        }

        @Test
        public void hasTheSameTopElementAfterPushingAndPoppingAnElement() {
            // given:
            final Stack<Long> tested =
                    (new Stack.Empty<Long>())
                            .push(1L)
                            .push(2L)
                            .push(3L);
            final long expected = tested.peek();
            // when:
            final long actual = tested.push(42L).pop().peek();
            // then:
            assertEquals(expected, actual);
        }
    }
}
