package org.artrev.workshop.junit4.stack.actions;

public interface Action<A> {
    A run(final A model);
}
