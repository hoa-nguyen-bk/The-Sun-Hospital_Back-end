package com.example.sun_hopital_back_end.services;

import com.example.sun_hopital_back_end.dto.SymptomDto;
import com.example.sun_hopital_back_end.payload.response.SymptomWithSpecializationResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SymptomServices {
    List<SymptomDto> getAllSymptoms(Integer pageNumber, Integer pageSize);
    SymptomDto createSymptom(SymptomDto symptomDto);
    SymptomDto updateSymptom(Integer id, SymptomDto symptomDto);

    @Transactional
    void deleteSymptom(Integer id);

    List<SymptomWithSpecializationResponse> getSymptomsWithSpecializations();
}
