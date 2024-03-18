package com.pluralsight.data.input.validation.medicalfacility.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConstraintViolation {
    
    private String fieldName;
    private String message;
    private String rejectedValue;
}
