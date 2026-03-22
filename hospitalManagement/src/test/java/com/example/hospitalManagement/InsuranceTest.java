package com.example.hospitalManagement;

import com.example.hospitalManagement.entity.Insurance;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;
    @Test
    public void testInsurance(){
//        Insurance insurance = new Insurance();
        Insurance insurance = Insurance.builder() // Create Insurance
                .policyNumber("HDFC_1234")
                .provider("Hdfc")
                .validUntil(LocalDate.of(2030,12,12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,1L); // Call Service // Controller/Test should call Service, NOT Repository
        System.out.println(patient);

         var newPatient =   insuranceService.dissassociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }
}
