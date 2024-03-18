package com.pluralsight.data.input.validation.medicalfacility.exceptionhandler;

import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pluralsight.data.input.validation.medicalfacility.dto.ConstraintViolation;
import com.pluralsight.data.input.validation.medicalfacility.exception.InvalidEmailException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ProblemDetail;

@RestControllerAdvice("com.pluralsight")
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEmailException.class)
    public ProblemDetail handleInvalidEmailException(
                                        InvalidEmailException exception) {
        return exception.toProblemDetail();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ProblemDetail handleUniquenessException(
            DataIntegrityViolationException exception) {
        if(!(exception.getCause() instanceof ConstraintViolationException)) {
            return handleAllExceptions(exception).getBody();
        }
        ConstraintViolationException constraintViolation =
                (ConstraintViolationException)exception.getCause();
        String message = determineConstraintViolationMessage(
                constraintViolation.getConstraintName());
        return ProblemDetail.forStatusAndDetail(
                BAD_REQUEST, message);
    }

    private String determineConstraintViolationMessage(String constraintName) {
        return "SSN must be unique";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        ProblemDetail validationProblemDetail = 
                ProblemDetail.forStatusAndDetail(
                BAD_REQUEST, "Validation error");

        List<ConstraintViolation> errors = exception.getFieldErrors()
                .stream()
                .map(violation -> ConstraintViolation.builder()
                        .message(violation.getDefaultMessage())
                        .fieldName(violation.getField())
                        .rejectedValue(Objects.isNull(
                                        violation.getRejectedValue()) ?
                                "null":
                                violation.getRejectedValue().toString())
                        .build())
                .collect(Collectors.toList());
        validationProblemDetail.setProperty("errors", errors);
        return validationProblemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(
                                        IllegalArgumentException exception) {
        return ProblemDetail.forStatusAndDetail(
                                BAD_REQUEST, exception.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAllExceptions(Exception exception) {
        return ErrorResponse
                .builder(exception, INTERNAL_SERVER_ERROR, 
                            exception.getMessage())
                .build();
    }
}
