package com.example.hospitalManagement;

import com.example.hospitalManagement.entity.Appointment;
import com.example.hospitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentService;


    @Test
    public void testCreateAppointment(){

        Appointment appointment = Appointment.builder().appointmentTime(LocalDateTime.of(2025,11,1,14,25))
                .reason("liver")
                .build();

       var newApoointment= appointmentService.createNewAppointment(appointment,1L,2L);

        System.out.println(newApoointment);

        var updateAppointment=appointmentService.reAssignAppointmentToAnotherDoctor(newApoointment.getId(),3L);

        System.out.println(updateAppointment);
    }

}
