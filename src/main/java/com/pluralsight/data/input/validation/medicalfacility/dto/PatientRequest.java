package com.pluralsight.data.input.validation.medicalfacility.dto;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.UUID;

import com.pluralsight.data.input.validation.medicalfacility.model.Patient;
import com.pluralsight.data.input.validation.medicalfacility.validation.ValidAddress;
import com.pluralsight.data.input.validation.medicalfacility.validation.validationgroup.OnCreate;
import com.pluralsight.data.input.validation.medicalfacility.validation.validationgroup.OnUpdate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PatientRequest {

    @NotNull
    private String firstName;

    @NotEmpty
    private String middleName;

    @NotBlank
    private String lastName;

    @Min(value = 18, message = "The age should not be lower than {value}")
    @Max(value = 150, message = "The age should not be higher than {value}")
    private Integer age;

    @Size(min=0, max= 10)
    private List<String> preexistingConditions;

    // @AssertFalse
    @AssertTrue(groups = OnCreate.class)
    @NotNull(groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    private Boolean consentGiven;

    @PositiveOrZero
    private Integer policyNumber;

    @FutureOrPresent
    private LocalDate registrationDate;

    @Past
    private LocalDate dateOfBirth;

    @Email
    private String email;

    @Pattern(regexp = "(A|B|AB|O)[+-]")
    private String bloodType;

    @UUID
    private String insurerId;

    @ValidAddress(value = StateFormat.ISO)
    private Address address;

    @NotBlank
    @Pattern(regexp = "[0-9]{3}-[0-9]{2}-[0-9]{4}")
    private String ssn;

    public Patient toEntity() {
        return Patient.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .age(age)
                .email(email)
                .bloodType(bloodType)
                .consentGiven(consentGiven)
                .preexistingConditions(preexistingConditions)
                .policyNumber(policyNumber)
                .registrationDate(registrationDate)
                .dateOfBirth(dateOfBirth)
                .insurerId(insurerId)
                .ssn(ssn)
                .build();
    }
}
