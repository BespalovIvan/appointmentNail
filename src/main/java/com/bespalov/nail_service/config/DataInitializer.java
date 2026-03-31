package com.bespalov.nail_service.config;

import com.bespalov.nail_service.entity.Role;
import com.bespalov.nail_service.entity.User;
import com.bespalov.nail_service.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setRole(Role.ROLE_ADMIN);
            admin.setEnabled(true);

            userRepo.save(admin);
            System.out.println("--- Администратор создан (admin/12345) ---");
        }
    }
}
