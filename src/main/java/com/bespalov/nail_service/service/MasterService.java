package com.bespalov.nail_service.service;


import com.bespalov.nail_service.dto.MasterDto;
import com.bespalov.nail_service.entity.Master;

public interface MasterService {
    Master saveMaster(MasterDto masterDto);
}
