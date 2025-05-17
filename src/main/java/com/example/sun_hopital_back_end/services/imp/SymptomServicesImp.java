package com.example.sun_hopital_back_end.services.imp;

import com.example.sun_hopital_back_end.dto.SymptomDto;
import com.example.sun_hopital_back_end.entity.Specialization;
import com.example.sun_hopital_back_end.entity.Symptoms;
import com.example.sun_hopital_back_end.repository.SpecializationRepository;
import com.example.sun_hopital_back_end.repository.SymptomRepository;
import com.example.sun_hopital_back_end.services.SymptomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SymptomServicesImp implements SymptomServices {
    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public List<SymptomDto> getAllSymptoms(Integer pageNumber, Integer pageSize) {
        List<Symptoms> symptoms;
        if (pageSize == null) {
            symptoms = symptomRepository.findAll();
        } else {
            int page = (pageNumber != null && pageNumber >= 0) ? pageNumber : 0;
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Symptoms> symptomsPage = symptomRepository.findAll(pageable);
            symptoms = symptomsPage.getContent();
        }

        List<SymptomDto> symptomDTOs = new ArrayList<>();
        for (Symptoms symptom : symptoms) {
            SymptomDto symptomDto = getSymptomDto(symptom);
            symptomDTOs.add(symptomDto);
        }
        return symptomDTOs;
    }

    private static SymptomDto getSymptomDto(Symptoms symptom) {
        SymptomDto symptomDto = new SymptomDto();
        symptomDto.setId(symptom.getId());
        symptomDto.setName(symptom.getName());
        symptomDto.setDescription(symptom.getDescription());
        if (symptom.getSpecialization() != null) {
            symptomDto.setSpecializationId(symptom.getSpecialization().getId());
            symptomDto.setSpecializationName(symptom.getSpecialization().getName());
        }
        symptomDto.setCreatedAt(symptom.getCreatedAt());
        symptomDto.setUpdatedAt(symptom.getUpdatedAt());
        symptomDto.setDeletedAt(symptom.getDeletedAt());
        return symptomDto;
    }

    @Override
    public SymptomDto createSymptom(SymptomDto symptomDto) {
        // Fetch Specialization by ID
        Optional<Specialization> specializationOpt = specializationRepository.findById(symptomDto.getSpecializationId());
        if (specializationOpt.isEmpty()) {
            throw new IllegalArgumentException("Specialization with ID " + symptomDto.getSpecializationId() + " not found");
        }
        Specialization specialization = specializationOpt.get();

        // Map DTO to Entity
        Symptoms symptom = new Symptoms();
        symptom.setName(symptomDto.getName());
        symptom.setDescription(symptomDto.getDescription());
        symptom.setSpecialization(specialization);
        // createdAt, updatedAt, deletedAt are handled by Hibernate annotations

        // Save Entity
        Symptoms savedSymptom = symptomRepository.save(symptom);

        // Map Entity back to DTO
        return getResultDto(savedSymptom);
    }

    private static SymptomDto getResultDto(Symptoms savedSymptom) {
        SymptomDto resultDto = new SymptomDto();
        resultDto.setId(savedSymptom.getId());
        resultDto.setName(savedSymptom.getName());
        resultDto.setDescription(savedSymptom.getDescription());
        resultDto.setSpecializationId(savedSymptom.getSpecialization().getId());
        resultDto.setSpecializationName(savedSymptom.getSpecialization().getName());
        resultDto.setCreatedAt(savedSymptom.getCreatedAt());
        resultDto.setUpdatedAt(savedSymptom.getUpdatedAt());
        resultDto.setDeletedAt(savedSymptom.getDeletedAt());
        return resultDto;
    }
}