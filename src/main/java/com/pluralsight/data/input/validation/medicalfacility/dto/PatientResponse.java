package com.pluralsight.data.input.validation.medicalfacility.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pluralsight.data.input.validation.medicalfacility.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(NON_NULL)
public class PatientResponse {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private String email;
    private String bloodType;
    private Boolean consentGiven;
    private List<String> preexistingConditions;
    private Integer policyNumber;
    private LocalDate registrationDate;
    private LocalDate dateOfBirth;
    private String insurerId;
    private String ssn;

    public static PatientResponse fromEntity(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .middleName(patient.getMiddleName())
                .lastName(patient.getLastName())
                .age(patient.getAge())
                .preexistingConditions(patient.getPreexistingConditions())
                .email(patient.getEmail())
                .bloodType(patient.getBloodType())
                .consentGiven(patient.getConsentGiven())
                .policyNumber(patient.getPolicyNumber())
                .registrationDate(patient.getRegistrationDate())
                .dateOfBirth(patient.getDateOfBirth())
                .insurerId(patient.getInsurerId())
                .ssn(patient.getSsn())
                .build();
    }
}
