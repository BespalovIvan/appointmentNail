package com.bespalov.nail_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GreetingController {
    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("message", "Добро пожаловать в MARY NAIL");
        return "greeting";
    }
}
