package com.bespalov.nail_service.repository;

import com.bespalov.nail_service.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepo extends JpaRepository<Client, UUID> {
}
