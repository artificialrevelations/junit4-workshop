package org.artrev.workshop.junit4.stack.actions;

import java.util.Arrays;
import java.util.List;

public class ActionSequence<A> implements Action<A> {
    private final List<Action<A>> actions;

    private ActionSequence(final Action<A>... actions) {
        this(Arrays.asList(actions));
    }

    private ActionSequence(final List<Action<A>> actions) {
        this.actions = actions;
    }

    @Override
    public A run(final A model) {
        A result = model;
        for (final Action<A> action : actions) {
            result = action.run(result);
        }
        return result;
    }

    @Override
    public String toString() {
        return actions.toString();
    }

    public static <A> ActionSequence<A> sequence(final Action<A>... actions) {
        return new ActionSequence<>(actions);
    }

    public static <A> ActionSequence<A> sequence(final List<Action<A>> actions) {
        return new ActionSequence<>(actions);
    }
}
