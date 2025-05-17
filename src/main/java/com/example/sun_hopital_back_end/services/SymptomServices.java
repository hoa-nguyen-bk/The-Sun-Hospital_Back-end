package com.example.sun_hopital_back_end.services;

import com.example.sun_hopital_back_end.dto.SymptomDto;

import java.util.List;

public interface SymptomServices {
    List<SymptomDto> getAllSymptoms(Integer pageNumber, Integer pageSize);
    SymptomDto createSymptom(SymptomDto symptomDto);
}
