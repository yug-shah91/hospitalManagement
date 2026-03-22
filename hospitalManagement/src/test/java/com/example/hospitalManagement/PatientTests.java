package com.example.hospitalManagement;

import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.repository.PatientRepository;
import com.example.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PatientTests {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientService patientService;
    @Test
    public void testPatientRepository(){
        List<Patient> patientList =  patientRepository.findAllPatientWithAppointment();
        System.out.println(patientList);

//        Patient p1 = new Patient();
//        patientRepository.save(p1);
    }

    @Test
    public void testTransactionMethods(){
//        Patient patient = patientService.getPatientById(1L);
//
     //   Patient patient = patientRepository.findById(1L).orElseThrow(()->new EntityNotFoundException("patient not"+"found with id : 1"));

       // Patient patient = patientRepository.findByName("abc");

//        List<Patient> patientList = patientRepository.
//                findByBirthDateOrEmail(LocalDate.of(2004,3,01),"abc@gmail.com");

//        List<Patient> patientList = patientRepository.
//                findByNameContaining("b");

//        List<Patient> patientList = patientRepository.
//                findByBloodGroup(BloodGroupType.A_POSITIVE);

//        List<Patient> patientList = patientRepository.
//                findByBornAfterDate(LocalDate.of(2005,1,1));

//        List<Patient> patientList = patientRepository.findAllPatiens();
//
//        for (Patient patient : patientList) {
//            System.out.println(patient);
//        }
//
//        List<Object[]> bloodGroupList = patientRepository.countBloodGroupType();
//        for (Object[] objects : bloodGroupList){
//            System.out.println(objects[0] + " " + objects[1 ]);
//        }

//        int rowsUpdated = patientRepository.updateNameWithId("yug shah",1L);
//        System.out.println(rowsUpdated);

//                List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
//        for (BloodGroupCountResponseEntity bloodGroupCountResponse : bloodGroupList){
//            System.out.println(bloodGroupCountResponse);
//        }

//                Page<Patient> patientList = patientRepository.findAllPatiens(PageRequest.of(0,2, Sort.by("id")));
//
//        for (Patient patient : patientList) {
//            System.out.println(patient);
//        }
    }
}
