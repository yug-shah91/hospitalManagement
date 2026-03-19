package com.example.hospitalManagement.service;

import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(Long id){

        Patient p1  = patientRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Patient not found"));

        Patient p2 = patientRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Patient not found"));

        System.out.println(p1==p2); // p1 and p2 are now same objects refernce bhi same jagha kar rahe ye sab persistence context ke thorugh ho raha

        p1.setName("yoyo");

        return p1;
    }
}
