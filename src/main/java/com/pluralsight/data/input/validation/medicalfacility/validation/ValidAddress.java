package com.pluralsight.data.input.validation.medicalfacility.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.pluralsight.data.input.validation.medicalfacility.dto.StateFormat;
import com.pluralsight.data.input.validation.medicalfacility.validation.validator.AddressValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = {AddressValidator.class})
public @interface ValidAddress {

    String message() default "Invalid or null Address provided";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    StateFormat value();
}
