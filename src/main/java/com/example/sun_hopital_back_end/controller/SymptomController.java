package com.example.sun_hopital_back_end.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/symptom")
public class SymptomController {
    @PostMapping
    public ResponseEntity<?> insertSymptom(){
        return ResponseEntity.ok("Hello insert symptoms");
    }
}
