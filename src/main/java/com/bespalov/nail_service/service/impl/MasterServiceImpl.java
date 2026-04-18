package com.bespalov.nail_service.service.impl;

import com.bespalov.nail_service.dto.MasterDto;
import com.bespalov.nail_service.dto.ProcedureDto;
import com.bespalov.nail_service.entity.*;
import com.bespalov.nail_service.exceptions.DbException;
import com.bespalov.nail_service.repository.AppointmentRepo;
import com.bespalov.nail_service.repository.MasterRepo;
import com.bespalov.nail_service.repository.ProcedureRepo;
import com.bespalov.nail_service.repository.UserRepo;
import com.bespalov.nail_service.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.bespalov.nail_service.entity.Role.ROLE_MASTER;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {
    private final MasterRepo masterRepo;
    private final UserRepo userRepo;
    private final ProcedureRepo procedureRepo;
    private final AppointmentRepo appointmentRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MasterDto saveMaster(MasterDto masterDto) {
        Master master = masterRepo.save(new Master(masterDto.getFirstName(), masterDto.getLastName()));
        User user = new User(master.getLastName(), passwordEncoder.encode("12345"), true,
                ROLE_MASTER, master.getId(), master.getFirstName());
        userRepo.save(user);
        return new MasterDto(master.getId(), master.getFirstName(), master.getLastName());
    }

    @Override
    public List<MasterDto> getAllMasters() {
        List<Master> masters = masterRepo.findAll();
        List<MasterDto> masterDtoList = new ArrayList<>();
        for (Master master : masters) {
            masterDtoList.add(new MasterDto(master.getId(), master.getFirstName(), master.getLastName()));
        }
        return masterDtoList;
    }

    @Override
    public MasterDto getMasterById(UUID id) {
        Master master = masterRepo.findById(id).orElseThrow(() -> new DbException("Мастер не найден!"));
        List<Procedure> procedureList = master.getProcedureList();
        List<ProcedureDto> procedureDtoList = new ArrayList<>();
        for (Procedure procedure : procedureList) {
            procedureDtoList.add(new ProcedureDto(procedure.getId(), procedure.getName(), procedure.getPrice(),
                    procedure.getDurationMinutes()));
        }
        return new MasterDto(master.getId(), master.getFirstName(), master.getLastName(), procedureDtoList);
    }

    @Override
    public MasterDto updateMasterProcedures(UUID masterId, List<UUID> procedureIds) {
        Master master = masterRepo.findById(masterId).orElseThrow(() -> new DbException("Мастер не найден!"));
        master.setProcedureList(procedureRepo.findAllById(procedureIds));
        masterRepo.save(master);
        return new MasterDto(master.getId(), master.getFirstName(), master.getLastName());
    }

    @Override
    public List<Appointment> getAllAppointmentByMasterId(UUID masterId) {
        Master master = masterRepo.findById(masterId).orElseThrow(() -> new DbException("Мастер не найден!"));
        return appointmentRepo.findAllByMasterId(master.getId());
    }
}
