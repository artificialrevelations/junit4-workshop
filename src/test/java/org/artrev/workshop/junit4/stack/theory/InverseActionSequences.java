package org.artrev.workshop.junit4.stack.theory;

import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(InverseActionSequencesSupplier.class)
public @interface InverseActionSequences {
}
