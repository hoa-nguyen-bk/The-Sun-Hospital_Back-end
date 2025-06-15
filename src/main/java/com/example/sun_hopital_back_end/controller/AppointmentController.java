package com.example.sun_hopital_back_end.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @PostMapping
    public ResponseEntity<?> insertAppointment(){
        return ResponseEntity.ok("Hello insert appointment");
    }
}
