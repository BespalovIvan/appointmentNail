package com.bespalov.nail_service.controller;

import com.bespalov.nail_service.dto.ProcedureDto;
import com.bespalov.nail_service.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProcedureController {
    private final ProcedureService procedureService;

    @GetMapping("/add-procedure")
    public String showAddProcedureForm(Model model) {
        model.addAttribute("procedureDto", new ProcedureDto());
        return "add-procedure";
    }

    @PostMapping("/add-procedure")
    public String addNewProcedure(@ModelAttribute ProcedureDto procedureDto) {
        procedureService.saveProcedure(procedureDto);
        return "redirect:/";
    }
}
