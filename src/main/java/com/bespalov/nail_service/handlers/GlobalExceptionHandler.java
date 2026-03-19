package com.bespalov.nail_service.handlers;

import com.bespalov.nail_service.exceptions.AppointmentException;
import com.bespalov.nail_service.exceptions.DbException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppointmentException.class)
    public String handleAppointmentException(AppointmentException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/make-appointment";
    }

    @ExceptionHandler(DbException.class)
    public String handleDbException(DbException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", " Ошибка данных: "
                + ex.getMessage());
        return "redirect:/";
    }
}
