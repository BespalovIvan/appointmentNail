package com.bespalov.nail_service.service;

import com.bespalov.nail_service.dto.ProcedureDto;
import com.bespalov.nail_service.entity.Procedure;

import java.util.List;

public interface ProcedureService {
    Procedure saveProcedure(ProcedureDto procedureDto);

    List<ProcedureDto> getAllProcedure();
}
