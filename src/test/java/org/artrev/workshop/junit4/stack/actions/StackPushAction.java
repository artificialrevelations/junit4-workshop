package org.artrev.workshop.junit4.stack.actions;

import org.artrev.workshop.junit4.stack.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
