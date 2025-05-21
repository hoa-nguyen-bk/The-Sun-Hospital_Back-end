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
    public ResponseEntity<?> getAllSymptoms(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setData(symptomServices.getAllSymptoms(pageNumber, pageSize));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/with-specialization")
    public ResponseEntity<?> getSymptomsWithSpecializations() {
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setData(symptomServices.getSymptomsWithSpecializations());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> insertNewSymptom(@Valid @RequestBody SymptomDto symptomDto) {
        BaseResponse response = new BaseResponse();
        response.setCode(201);
        response.setData(symptomServices.createSymptom(symptomDto));
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSymptom(@PathVariable Integer id, @Valid @RequestBody SymptomDto symptomDto) {
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setData(symptomServices.updateSymptom(id, symptomDto));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSymptom(@PathVariable Integer id) {
        symptomServices.deleteSymptom(id);
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setData("Symptom deleted successfully");
        return ResponseEntity.ok(response);
    }
}