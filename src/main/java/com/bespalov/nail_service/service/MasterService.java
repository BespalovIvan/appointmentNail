package com.bespalov.nail_service.service;


import com.bespalov.nail_service.dto.MasterDto;
import com.bespalov.nail_service.entity.Appointment;

import java.util.List;
import java.util.UUID;

public interface MasterService {
    MasterDto saveMaster(MasterDto masterDto);

    List<MasterDto> getAllMasters();

    MasterDto getMasterById(UUID id);

    MasterDto updateMasterProcedures(UUID masterId, List<UUID> procedureIds);

    List<Appointment> getAllAppointmentByMasterId(UUID masterId);
}
