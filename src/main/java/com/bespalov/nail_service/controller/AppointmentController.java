package com.bespalov.nail_service.controller;

import com.bespalov.nail_service.dto.AppointmentDto;
import com.bespalov.nail_service.entity.User;
import com.bespalov.nail_service.service.AppointmentService;
import com.bespalov.nail_service.service.ClientService;
import com.bespalov.nail_service.service.MasterService;
import com.bespalov.nail_service.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final ClientService clientService;
    private final MasterService masterService;
    private final ProcedureService procedureService;

    @GetMapping("/make-appointment")
    public String showAppointmentForm(Model model) {
        if (!model.containsAttribute("appointmentDto")) {
            model.addAttribute("appointmentDto", new AppointmentDto());
        }
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("masters", masterService.getAllMasters());
        model.addAttribute("procedures", procedureService.getAllProcedure());
        return "make-appointment";
    }

    @PostMapping("/make-appointment")
    public String createAppointment(@ModelAttribute AppointmentDto appointmentDto) {
        appointmentService.createAppointment(appointmentDto);
        return "redirect:/?success=true";
    }

    @GetMapping("/appointments")
    public String showAllAppointment(Model model, @AuthenticationPrincipal User user) {
        switch (user.getRole()) {
            case ROLE_ADMIN -> model.addAttribute("appointments",
                    appointmentService.getAllAppointment());
            case ROLE_MASTER -> model.addAttribute("appointments",
                    masterService.getAllAppointmentByMasterId(user.getPersonId()));
        }

        return "appointments-list";
    }

    @PostMapping("/appointments/delete/{appointmentId}")
    public String deleteAppointment(@PathVariable UUID appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return "redirect:/appointments";
    }
}
