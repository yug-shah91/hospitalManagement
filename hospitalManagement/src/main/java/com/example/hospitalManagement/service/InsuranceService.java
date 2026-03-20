package com.example.hospitalManagement.service;

import com.example.hospitalManagement.entity.Insurance;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.repository.InsuranceRepository;
import com.example.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    private final PatientRepository patientRepository;


    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance , Long patientId){

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new EntityNotFoundException("Patient not found with id:"+patientId));

        patient.setInsurance(insurance);

        insurance.setPatient(patient);

        return patient;

    }

}
