package com.bespalov.nail_service.repository;

import com.bespalov.nail_service.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcedureRepo extends JpaRepository<Procedure, UUID> {
    boolean existsByName(String name);
}
