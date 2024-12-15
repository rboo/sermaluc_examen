package org.example.examen.domain.model.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "La clave no cumple con el formato requerido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
