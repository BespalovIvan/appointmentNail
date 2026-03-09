package com.bespalov.nail_service.controller;

import com.bespalov.nail_service.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ClientService clientService;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("message", "Добро пожаловать в MARY NAIL");
        return "greeting";
    }
}
