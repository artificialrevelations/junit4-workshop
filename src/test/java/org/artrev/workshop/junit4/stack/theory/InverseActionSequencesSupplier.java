package org.artrev.workshop.junit4.stack.theory;

import org.artrev.workshop.junit4.stack.Stack;
import org.artrev.workshop.junit4.stack.actions.Action;
import org.artrev.workshop.junit4.stack.actions.ActionSequence;
import org.artrev.workshop.junit4.stack.actions.StackPopAction;
import org.artrev.workshop.junit4.stack.actions.StackPushAction;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InverseActionSequencesSupplier extends ParameterSupplier {
    private static final String ASSIGNMENT_VALUE_NAME = "ints_above";

    @Override
    public List<PotentialAssignment> getValueSources(final ParameterSignature sig) throws Throwable {
        // if the annotation is needed to pass configuration arguments
        // final InverseActionSequences annotation = sig.getAnnotation(InverseActionSequences.class);

        final List<PotentialAssignment> assignments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            assignments.add(getRandomAssignment());
        }

        return assignments;
    }

    private PotentialAssignment getRandomAssignment() {
        final Random random = new Random();
        final List<Action<Stack<String>>> actions = new ArrayList<>();
        actions.add(new StackPushAction<>("root element "));
        int stack = 1;
        for (int i = 0; i < 5; i++) {
            if (random.nextBoolean()) {
                actions.add(new StackPushAction<>("element " + i));
                stack++;
            } else if (stack > 1) {
                actions.add(new StackPopAction<>());
                stack--;
            }
        }

        for (int i = 0; i < stack; i++) {
            actions.add(new StackPopAction<>());
        }

        return PotentialAssignment.forValue(
                ASSIGNMENT_VALUE_NAME,
                ActionSequence.sequence(actions)
        );
    }
}
