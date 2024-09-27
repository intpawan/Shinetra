package com.contact.contact.controller;

import com.contact.contact.service.EmailService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@Validated
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendContactForm(
            @RequestParam("email") @Email @NotEmpty String email,
            @RequestParam("phone") @NotEmpty String phone,
            @RequestParam("message") @NotEmpty String message) {

        if (email.isEmpty() || phone.isEmpty() || message.isEmpty()) {
            return new ResponseEntity<>("Please fill all the fields", HttpStatus.BAD_REQUEST);
        }

        // Send email
        emailService.sendEmail("pl.infosystem@gmail.com", "New Contact Form Submission",
                "Email: " + email + "\nPhone: " + phone + "\nMessage: " + message);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Email sent successfully!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}