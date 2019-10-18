package org.artrev.workshop.junit4.stack.actions;

import org.artrev.workshop.junit4.stack.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StackPopAction<A> implements Action<Stack<A>> {

    @Override
    public Stack<A> run(final Stack<A> model) {
        assertFalse(model.isEmpty());
        final int depthBefore = model.getDepth();
        final Stack<A> result = model.pop();
        assertEquals(depthBefore - 1, result.getDepth());
        return result;
    }
}
