package com.example.sun_hopital_back_end.services;

import com.example.sun_hopital_back_end.dto.SpecializationDto;

import java.util.List;

public interface SpecializationServices {
    List<SpecializationDto> getAllSpecializations(Integer pageNumber, Integer pageSize);
    SpecializationDto createSpecialization(SpecializationDto specializationDto);
    SpecializationDto updateSpecialization(Integer id, SpecializationDto specializationDto);
    void deleteSpecialization(Integer id);
}