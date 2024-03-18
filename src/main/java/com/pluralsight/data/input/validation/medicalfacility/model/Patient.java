package com.pluralsight.data.input.validation.medicalfacility.model;

import java.time.LocalDate;
import java.util.List;

import com.pluralsight.data.input.validation.medicalfacility.dto.PatientRequest;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "patient")
@Entity
public class Patient {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "consent_given")
    private Boolean consentGiven;

    @Column(name = "preexisting_conditions")
    @ElementCollection
    private List<String> preexistingConditions;

    @Column(name = "policy_number")
    private Integer policyNumber;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "insurer_id")
    private String insurerId;

    @Column(name = "ssn")
    private String ssn;

    public Patient updatePatient(PatientRequest patientRequest) {
        setFirstName(patientRequest.getFirstName());
        setMiddleName(patientRequest.getMiddleName());
        setLastName(patientRequest.getLastName());
        setAge(patientRequest.getAge());
        setEmail(patientRequest.getEmail());
        setBloodType(patientRequest.getBloodType());
        setConsentGiven(patientRequest.getConsentGiven());
        setPreexistingConditions(patientRequest.getPreexistingConditions());
        setPolicyNumber(patientRequest.getPolicyNumber());
        setRegistrationDate(patientRequest.getRegistrationDate());
        setDateOfBirth(patientRequest.getDateOfBirth());
        setInsurerId(patientRequest.getInsurerId());
        setSsn(patientRequest.getSsn());
        return this;
    }
}
