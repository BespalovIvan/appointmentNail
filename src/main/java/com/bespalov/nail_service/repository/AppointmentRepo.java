package com.bespalov.nail_service.repository;

import com.bespalov.nail_service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {
}
