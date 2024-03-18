package com.pluralsight.data.input.validation.medicalfacility.validation.validator;

import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pluralsight.data.input.validation.medicalfacility.dto.Address;
import com.pluralsight.data.input.validation.medicalfacility.dto.StateFormat;
import com.pluralsight.data.input.validation.medicalfacility.validation.ValidAddress;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<ValidAddress, Address> {

    private static final Map<StateFormat, Pattern> STATE_FORMAT_REGEX =
            Map.of(StateFormat.ANSI, Pattern.compile("^[A-Z]{2}$"),
                    StateFormat.ISO, Pattern.compile("^[A-Z]{2}-[A-Z]{2}$"));

    private StateFormat stateFormat;

    @Override
    public void initialize(ValidAddress constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.stateFormat = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Address value, ConstraintValidatorContext context) {
        if( isNull(value)) return false;
        if( isBlank(value.getName()) || isBlank(value.getCity())) return false;

        Matcher matcher = STATE_FORMAT_REGEX.get(stateFormat)
                .matcher(value.getState());
                if(!matcher.matches()) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Invalid State Name; Please use the " + stateFormat + " format")
                            .addPropertyNode("state")
                            .addConstraintViolation();
                    return false;
                }
                return true;
    }
}

