package com.bespalov.nail_service.repository;

import com.bespalov.nail_service.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MasterRepo extends JpaRepository<Master, UUID> {
}
