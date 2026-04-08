package com.example.hospitalManagement.service;

import com.example.hospitalManagement.dto.AppointmentResponseDto;
import com.example.hospitalManagement.dto.CreateAppointmentRequestDto;
import com.example.hospitalManagement.entity.Appointment;
import com.example.hospitalManagement.entity.Doctor;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.repository.AppointmentRepository;
import com.example.hospitalManagement.repository.DoctorRepository;
import com.example.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Secured("ROLE_PATIENT")
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
    @PreAuthorize("hasAuthority('appointment:write') or #doctorId == authentication.principal.id")
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId , Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow();
        Doctor doctor= doctorRepository.findById(doctorId)
                .orElseThrow();

        appointment.setDoctor(doctor); // this will automatically call the update because it is dirty now

        doctor.getAppointments().add(appointment);
        return appointment;
    }

    @PreAuthorize("hasRole('ADMIN') OR (hasRole('DOCTOR')) AND #doctorId == authentication.principal.id")
    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);

        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .toList();
    }

    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto requestDto) {
        // 1. Convert the Request DTO into an Appointment Entity
        Appointment appointment = modelMapper.map(requestDto, Appointment.class);

        // 2. Fetch related entities (assuming your DTO has doctorId and patientId)
        // Doctor doctor = doctorRepository.findById(requestDto.getDoctorId()).orElseThrow();
        // Patient patient = patientRepository.findById(requestDto.getPatientId()).orElseThrow();
        // appointment.setDoctor(doctor);
        // appointment.setPatient(patient);

        // 3. Save to database
        Appointment savedAppointment = appointmentRepository.save(appointment);

        // 4. Return the result as a Response DTO
        return modelMapper.map(savedAppointment, AppointmentResponseDto.class);
    }
}
