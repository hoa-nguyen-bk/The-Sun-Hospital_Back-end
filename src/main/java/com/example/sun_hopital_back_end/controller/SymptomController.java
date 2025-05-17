package com.example.sun_hopital_back_end.controller;

import com.example.sun_hopital_back_end.dto.SymptomDto;
import com.example.sun_hopital_back_end.payload.response.BaseResponse;
import com.example.sun_hopital_back_end.services.SymptomServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/symptom")
public class SymptomController {
    @Autowired
    private SymptomServices symptomServices;

    @GetMapping
    public ResponseEntity<?> getAllSymptom(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
//        System.out.println("Received request for getAllSymptom, pageNumber: " + pageNumber + ", pageSize: " + (pageSize != null ? pageSize : "all"));
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setData(symptomServices.getAllSymptoms(pageNumber, pageSize));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> insertNewSymptom(@Valid @RequestBody SymptomDto symptomDto) {
        BaseResponse response = new BaseResponse();
        response.setCode(201);
        response.setData(symptomServices.createSymptom(symptomDto));
        return ResponseEntity.status(201).body(response);
    }
}