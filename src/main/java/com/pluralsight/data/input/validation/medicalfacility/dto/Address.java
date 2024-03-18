package com.pluralsight.data.input.validation.medicalfacility.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
}
