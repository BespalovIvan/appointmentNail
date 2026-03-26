package com.bespalov.nail_service.service;

import com.bespalov.nail_service.dto.AppointmentDto;
import com.bespalov.nail_service.entity.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    void createAppointment(AppointmentDto appointmentDto);
    List<Appointment> getAllAppointment();
    void deleteAppointment(UUID appointmentId);
}
