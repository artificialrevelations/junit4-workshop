package org.artrev.workshop.junit4.stack.actions;

import org.artrev.workshop.junit4.stack.Stack;

public final class Actions {
    public static <A> Stack<A> perform(final Stack<A> stack,
                                       final int times,
                                       final Action<Stack<A>> action) {
        Stack<A> result = stack;
        for (int i = 0; i < times; i++) {
            result = action.run(result);
        }
        return result;
    }
}
