package org.example.validation;
/// validaciones custom (Anotación)
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDnaSequenceValidator.class)
@Documented
public @interface ValidDnaSequence {

    String message() default "Invalid DNA sequence. Must be NxN and contain only A, T, C or G.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
