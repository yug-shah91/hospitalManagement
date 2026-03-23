package com.example.hospitalManagement.service;

import com.example.hospitalManagement.entity.Appointment;
import com.example.hospitalManagement.entity.Doctor;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.repository.AppointmentRepository;
import com.example.hospitalManagement.repository.DoctorRepository;
import com.example.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment , Long patientId,Long doctorId){

        Doctor doctor= doctorRepository.findById(doctorId)
                .orElseThrow();

        Patient patient = patientRepository.findById(doctorId)
                .orElseThrow();

        if (appointment.getId()!=null) throw new IllegalArgumentException("Appointment should not have");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);

        return appointmentRepository.save(appointment);
    }
    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId , Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow();
        Doctor doctor= doctorRepository.findById(doctorId)
                .orElseThrow();

        appointment.setDoctor(doctor); // this will automatically call the update because it is dirty now

        doctor.getAppointments().add(appointment);
        return appointment;
    }
}
