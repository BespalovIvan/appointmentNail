package com.bespalov.nail_service.controller;

import com.bespalov.nail_service.dto.MasterDto;
import com.bespalov.nail_service.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MasterController {
    private final MasterService masterService;

    @GetMapping("/add-master")
    public String ShowAddMasterForm(Model model) {
        model.addAttribute("masterDto", new MasterDto());
        return "add-master";
    }

    @PostMapping("/add-master")
    public String registerMaster(@ModelAttribute MasterDto masterDto) {
        masterService.saveMaster(masterDto);
        return "redirect:/";
    }
}
