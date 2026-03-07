package com.bespalov.nail_service.service;

import com.bespalov.nail_service.dto.ClientDto;
import com.bespalov.nail_service.entity.Client;

import java.util.List;

public interface ClientService {
    Client saveClient(ClientDto clientDto);

    List<ClientDto> getAllClients();
}
