package com.bespalov.nail_service.service.impl;

import com.bespalov.nail_service.dto.ClientDto;
import com.bespalov.nail_service.entity.Client;
import com.bespalov.nail_service.exceptions.RegistrationException;
import com.bespalov.nail_service.repository.ClientRepo;
import com.bespalov.nail_service.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepo clientRepo;

    @Override
    public Client saveClient(ClientDto clientDto) {
        if (clientRepo.existsByPhoneNumber(clientDto.getPhoneNumber())) {
            throw new RegistrationException("Клиент уже создан!");
        }
        return clientRepo.save(new Client(clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getPhoneNumber()));
    }

    @Override
    public List<ClientDto> getAllClients() {
        List<Client> clientList = clientRepo.findAll();
        List<ClientDto> clientDtos = new ArrayList<>();
        for (Client client : clientList) {
            clientDtos.add(new ClientDto(client.getId(), client.getFirstName(), client.getLastName(), client.getPhoneNumber()));
        }
        return clientDtos;
    }
}
