package org.artrev.workshop.junit4.stack.actions;

import org.artrev.workshop.junit4.stack.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Describes a {@link Stack#pop()} in a form of an effectful action.
 * Performs basic checks of the properties of a given {@link Stack}:
 * <ul>
 * <li>if the given stack is not empty before the pop operation</li>
 * <li>if the depth of the stack after pop has decreased by one</li>
 * </ul>
 * @param <A>
 */
public class StackPopAction<A> implements Action<Stack<A>> {
    @Override
    public Stack<A> run(final Stack<A> model) {
        assertFalse(model.isEmpty());
        final int depthBefore = model.getDepth();
        final Stack<A> result = model.pop();
        assertEquals(depthBefore - 1, result.getDepth());
        return result;
    }

    @Override
    public String toString() {
        return "StackPopAction";
    }
}
