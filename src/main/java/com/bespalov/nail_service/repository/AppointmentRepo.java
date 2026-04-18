package com.bespalov.nail_service.repository;

import com.bespalov.nail_service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {
    @Query(value = """
            SELECT COUNT(*) > 0
            FROM appointments a
            JOIN procedures p ON a.procedure_id = p.id
            WHERE a.master_id = :masterId
            AND :newStart < (a.date_of_procedure + (p.duration_minutes || ' minutes')::interval)
            AND :newEnd > a.date_of_procedure
            """, nativeQuery = true)
    boolean isMasterBusy(
            @Param("masterId") UUID masterId,
            @Param("newStart") LocalDateTime newStart,
            @Param("newEnd") LocalDateTime newEnd
    );

    List<Appointment> findAllByMasterId(UUID masterId);
}
