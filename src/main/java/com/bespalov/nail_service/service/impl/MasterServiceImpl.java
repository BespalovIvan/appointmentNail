package com.bespalov.nail_service.service.impl;

import com.bespalov.nail_service.dto.MasterDto;
import com.bespalov.nail_service.entity.Master;
import com.bespalov.nail_service.repository.MasterRepo;
import com.bespalov.nail_service.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {
    private final MasterRepo masterRepo;

    @Override
    public Master saveMaster(MasterDto masterDto) {
        return masterRepo.save(new Master(masterDto.getFirstName(), masterDto.getLastName()));
    }
}
