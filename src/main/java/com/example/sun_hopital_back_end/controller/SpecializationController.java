package com.example.sun_hopital_back_end.controller;

import com.example.sun_hopital_back_end.dto.SpecializationDto;
import com.example.sun_hopital_back_end.payload.response.BaseResponse;
import com.example.sun_hopital_back_end.services.SpecializationServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/specialization")
public class SpecializationController {
    @Autowired
    private SpecializationServices specializationServices;

    @GetMapping
    public ResponseEntity<?> getAllSpecializations(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setData(specializationServices.getAllSpecializations(pageNumber, pageSize));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createSpecialization(@Valid @RequestBody SpecializationDto specializationDto) {
        BaseResponse response = new BaseResponse();
        response.setCode(201);
        response.setData(specializationServices.createSpecialization(specializationDto));
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSpecialization(@PathVariable Integer id, @Valid @RequestBody SpecializationDto specializationDto) {
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setData(specializationServices.updateSpecialization(id, specializationDto));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpecialization(@PathVariable Integer id) {
        specializationServices.deleteSpecialization(id);
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setData("Specialization deleted successfully");
        return ResponseEntity.ok(response);
    }
}