package com.bespalov.nail_service.controller;

import com.bespalov.nail_service.dto.ClientDto;
import com.bespalov.nail_service.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/add-client")
    public String showAddClientForm(Model model) {
        model.addAttribute("clientDto", new ClientDto());
        return "add-client";
    }

    @PostMapping("/add-client")
    public String registerClient(@ModelAttribute ClientDto clientDto) {
        clientService.saveClient(clientDto);
        return "redirect:/";
    }
}
