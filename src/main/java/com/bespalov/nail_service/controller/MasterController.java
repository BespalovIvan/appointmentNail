package com.bespalov.nail_service.controller;

import com.bespalov.nail_service.dto.MasterDto;
import com.bespalov.nail_service.service.MasterService;
import com.bespalov.nail_service.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MasterController {
    private final MasterService masterService;
    private final ProcedureService procedureService;

    @GetMapping("/add-master")
    public String showAddMasterForm(Model model) {
        model.addAttribute("masterDto", new MasterDto());
        return "add-master";
    }

    @PostMapping("/add-master")
    public String registerMaster(@ModelAttribute MasterDto masterDto) {
        masterService.saveMaster(masterDto);
        return "redirect:/";
    }

    @GetMapping("/masters")
    public String getAllMasters(Model model) {
        model.addAttribute("masters", masterService.getAllMasters());
        return "masters-list";
    }

    @GetMapping("/master/{id}/procedures")
    public String getProceduresByMaster(@PathVariable UUID id, Model model) {
        model.addAttribute("master", masterService.getMasterById(id));
        return "master-procedures";
    }

    @GetMapping("/master/{id}/edit-procedures")
    public String showEditMasterProceduresForm(@PathVariable UUID id, Model model) {
        model.addAttribute("master", masterService.getMasterById(id));
        model.addAttribute("procedures", procedureService.getAllProcedure());
        return "edit-master-procedures";
    }

    @PostMapping("/master/{id}/edit-procedures")
    public String editMasterProcedures(@PathVariable UUID id, @RequestParam List<UUID> selectedProcedures) {
        masterService.updateMasterProcedures(id, selectedProcedures);
        return "redirect:/masters";
    }
}
