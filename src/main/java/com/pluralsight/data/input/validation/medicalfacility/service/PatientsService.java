package com.pluralsight.data.input.validation.medicalfacility.service;

import com.pluralsight.data.input.validation.medicalfacility.dto.PatientRequest;
import com.pluralsight.data.input.validation.medicalfacility.dto.PatientResponse;
import com.pluralsight.data.input.validation.medicalfacility.model.Patient;
import com.pluralsight.data.input.validation.medicalfacility.repository.PatientsRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

import static com.pluralsight.data.input.validation.medicalfacility.dto.PatientResponse.fromEntity;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PatientsService {

    private PatientsRepository patientsRepository;

    public List<PatientResponse> getAllPatients() {
        return patientsRepository.findAll().stream()
                .map(patient -> fromEntity(patient))
                .collect(toList());
    }

    public PatientResponse getPatientById(Long id) {
        return patientsRepository.findById(id)
                .map(patient -> fromEntity(patient))
                .orElseThrow();
    }

    public PatientResponse createPatient(PatientRequest patientRequest) {
        Patient patient = patientsRepository.save(patientRequest.toEntity());
        return fromEntity(patient);
    }

    public PatientResponse updatePatient(Long id, PatientRequest patientRequest) {
        return patientsRepository.findById(id)
                .map(patient -> patient.updatePatient(patientRequest))
                .map(patient -> patientsRepository.save(patient))
                .map(patient -> fromEntity(patient))
                .orElseThrow(() -> new IllegalArgumentException("Patient with id " + id + " not found"));
    }
}
