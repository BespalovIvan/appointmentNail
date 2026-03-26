package com.bespalov.nail_service.service.impl;

import com.bespalov.nail_service.dto.AppointmentDto;
import com.bespalov.nail_service.entity.Appointment;
import com.bespalov.nail_service.entity.Client;
import com.bespalov.nail_service.entity.Master;
import com.bespalov.nail_service.entity.Procedure;
import com.bespalov.nail_service.exceptions.AppointmentException;
import com.bespalov.nail_service.exceptions.DbException;
import com.bespalov.nail_service.repository.AppointmentRepo;
import com.bespalov.nail_service.repository.ClientRepo;
import com.bespalov.nail_service.repository.MasterRepo;
import com.bespalov.nail_service.repository.ProcedureRepo;
import com.bespalov.nail_service.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final ProcedureRepo procedureRepo;
    private final AppointmentRepo appointmentRepo;
    private final ClientRepo clientRepo;
    private final MasterRepo masterRepo;

    @Override
    public void createAppointment(AppointmentDto appointmentDto) {
        Procedure procedure = procedureRepo.findById(appointmentDto.getProcedureId())
                .orElseThrow(() -> new DbException("Услуга не найдена"));
        LocalDateTime startAppointment = appointmentDto.getDateOfProcedure();
        LocalDateTime endAppointment = startAppointment.plusMinutes(procedure.getDurationMinutes());
        boolean masterIsBusy = appointmentRepo.isMasterBusy(appointmentDto.getMasterId(),
                startAppointment, endAppointment);
        if (masterIsBusy) {
            throw new AppointmentException("Мастер занят на это время");
        }
        Client client = clientRepo.findById(appointmentDto.getClientId())
                .orElseThrow(() -> new DbException("Клиент не найден"));
        Master master = masterRepo.findById(appointmentDto.getMasterId())
                .orElseThrow(() -> new DbException("Мастер не найден"));
        appointmentRepo.save(new Appointment(client, master, procedure, appointmentDto.getDateOfProcedure()));
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepo.findAll();
    }

    @Override
    public void deleteAppointment(UUID appointmentId) {
        appointmentRepo.deleteById(appointmentId);
    }
}
