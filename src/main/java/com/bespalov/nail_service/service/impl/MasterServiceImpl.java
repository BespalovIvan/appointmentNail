package com.bespalov.nail_service.service.impl;

import com.bespalov.nail_service.dto.MasterDto;
import com.bespalov.nail_service.dto.ProcedureDto;
import com.bespalov.nail_service.entity.Master;
import com.bespalov.nail_service.entity.Procedure;
import com.bespalov.nail_service.exceptions.DbException;
import com.bespalov.nail_service.repository.MasterRepo;
import com.bespalov.nail_service.repository.ProcedureRepo;
import com.bespalov.nail_service.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {
    private final MasterRepo masterRepo;
    private final ProcedureRepo procedureRepo;

    @Override
    public MasterDto saveMaster(MasterDto masterDto) {
        Master master = masterRepo.save(new Master(masterDto.getFirstName(), masterDto.getLastName()));
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
}
