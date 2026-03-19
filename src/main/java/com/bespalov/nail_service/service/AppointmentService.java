package com.bespalov.nail_service.service;

import com.bespalov.nail_service.dto.AppointmentDto;

public interface AppointmentService {
    void createAppointment(AppointmentDto appointmentDto);
}
