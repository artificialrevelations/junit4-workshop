package org.artrev.workshop.junit4.stack.actions;

import org.artrev.workshop.junit4.stack.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Describes a {@link Stack#push(Object)} in a form of an effectful action.
 * Performs basic checks of the properties of a given {@link Stack}:
 * <ul>
 * <li>if the stack is not empty after push an element</li>
 * <li>if the the element that is now the top of the stack is the same as
 * the specified</li>
 * <li>if the depth of the given stack has increased by one</li>
 * </ul>
 * If any of the specified properties is not satisfied then this action
 * will immediately fail the test.
 *
 * @param <A> type of the elements stored on the stack.
 */
public class StackPushAction<A> implements Action<Stack<A>> {
    private final A element;

    public StackPushAction(final A element) {
        this.element = element;
    }

    @Override
    public Stack<A> run(final Stack<A> model) {
        final Stack<A> result = model.push(element);
        final int depthBefore = model.getDepth();
        assertFalse(result.isEmpty());
        assertEquals(element, model.peek());
        assertEquals(depthBefore + 1, model.getDepth());
        return result;
    }
}
