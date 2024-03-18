package com.pluralsight.data.input.validation.medicalfacility.exception;

import org.springframework.http.ProblemDetail;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String email) {
        super("Invalid email: " + email);
    }

    public ProblemDetail toProblemDetail() {
        return ProblemDetail.forStatusAndDetail(
            BAD_REQUEST, this.getMessage());
    }
}
