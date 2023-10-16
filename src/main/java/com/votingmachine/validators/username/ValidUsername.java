package com.votingmachine.validators.username;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsenameValidator.class)
@Documented
public @interface ValidUsername {
	String message() default "username should contain all lower case values, and can have digits and '_'";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
