package org.artrev.workshop.junit4.stack;

import java.util.NoSuchElementException;

/**
 * Fully immutable implementation of a stack.
 *
 * @param <A> type of the elements stored.
 */
public abstract class Stack<A> {
    private Stack() {
    }

    /**
     * Returns an instance of the stack that is below the current element. If
     * the stack has only one element then an {@link Stack.Empty} will be returned.
     * If stack is empty then an exception will get thrown.
     *
     * @return instance of the {@link Stack} that is below the current element
     * or throws an Exception if stack is empty.
     */
    public abstract Stack<A> pop();

    /**
     * Puts a specified element on the stack and returns reference to the new
     * instance.
     *
     * @param element element that should be placed on the stack.
     * @return instance of the {@link Stack} that is created after placing a new
     * element.
     */
    public abstract Stack<A> push(final A element);

    /**
     * Returns information if the {@link Stack} is empty.
     *
     * @return true if this is an {@link Empty}, false otherwise.
     */
    public abstract boolean isEmpty();

    /**
     * Returns the top element that is stored on the stack. If the stack is
     * empty then an exception is thrown.
     *
     * @return top element of the stack if available, throws exception otherwise.
     */
    public abstract A peek();

    /**
     * Returns the depth of the stack. If the stack is empty returns 0.
     *
     * @return depth of the stack, if empty 0.
     */
    public abstract int getDepth();

    /**
     * Represents a non empty stack node.
     *
     * @param <A> type of the stored element.
     */
    public static final class NonEmpty<A> extends Stack<A> {
        private final Stack<A> previous;
        private final A element;

        public NonEmpty(final Stack<A> previous,
                        final A element) {
            this.previous = previous;
            this.element = element;
        }

        @Override
        public Stack<A> pop() {
            return previous;
        }

        @Override
        public Stack<A> push(final A element) {
            return new NonEmpty<>(this, element);
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public A peek() {
            return element;
        }

        @Override
        public int getDepth() {
            return 1 + previous.getDepth();
        }
    }

    /**
     * Represents empty stack node.
     *
     * @param <A> type of the stored element.
     */
    public static final class Empty<A> extends Stack<A> {

        @Override
        public Stack<A> pop() {
            throw new IllegalStateException("Cannot pop an element from an empty stack!");
        }

        @Override
        public Stack<A> push(final A element) {
            return new NonEmpty<>(this, element);
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public A peek() {
            throw new NoSuchElementException("Empty stack does not have any elements!");
        }

        @Override
        public int getDepth() {
            return 0;
        }
    }
}
