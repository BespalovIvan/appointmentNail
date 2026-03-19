package com.bespalov.nail_service.service.impl;

import com.bespalov.nail_service.dto.ProcedureDto;
import com.bespalov.nail_service.entity.Procedure;
import com.bespalov.nail_service.exceptions.RegistrationException;
import com.bespalov.nail_service.repository.ProcedureRepo;
import com.bespalov.nail_service.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {
    private final ProcedureRepo procedureRepo;

    @Override
    public Procedure saveProcedure(ProcedureDto procedureDto) {
        if (procedureRepo.existsByName(procedureDto.getName())) {
            throw new RegistrationException("Услуга уже создана!");
        }
        return procedureRepo.save(new Procedure(procedureDto.getName(), procedureDto.getPrice(),
                procedureDto.getDurationMinutes()));
    }

    @Override
    public List<ProcedureDto> getAllProcedure() {
        List<ProcedureDto> procedureDtoList = new ArrayList<>();
        List<Procedure> procedureList = procedureRepo.findAll();
        for (Procedure procedure : procedureList) {
            procedureDtoList.add(new ProcedureDto(procedure.getId(), procedure.getName(), procedure.getPrice(),
                    procedure.getDurationMinutes()));
        }
        return procedureDtoList;
    }
}
