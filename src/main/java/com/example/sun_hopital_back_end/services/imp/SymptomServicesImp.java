package com.example.sun_hopital_back_end.services.imp;

import com.example.sun_hopital_back_end.dto.SymptomDto;
import com.example.sun_hopital_back_end.payload.response.SymptomWithSpecializationResponse;
import com.example.sun_hopital_back_end.entity.Specialization;
import com.example.sun_hopital_back_end.entity.Symptoms;
import com.example.sun_hopital_back_end.repository.SpecializationRepository;
import com.example.sun_hopital_back_end.repository.SymptomRepository;
import com.example.sun_hopital_back_end.services.SymptomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        return symptoms.stream()
                .map(this::mapToSymptomDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SymptomDto createSymptom(SymptomDto symptomDto) {
        Specialization specialization;

        // If specialization ID is provided, try to find it
        if (symptomDto.getSpecializationId() != null) {
            Optional<Specialization> specializationOpt = specializationRepository.findById(symptomDto.getSpecializationId());
            // If ID provided but not found, create new specialization
            specialization = specializationOpt.orElseGet(() -> createNewSpecialization(symptomDto));
        } else if (symptomDto.getSpecializationName() != null && !symptomDto.getSpecializationName().isEmpty()) {
            // If no ID but name provided, create new specialization
            specialization = createNewSpecialization(symptomDto);
        } else {
            throw new IllegalArgumentException("Either specialization ID or name must be provided");
        }

        Symptoms symptom = new Symptoms();
        symptom.setName(symptomDto.getName());
        symptom.setDescription(symptomDto.getDescription());
        symptom.setSpecialization(specialization);

        Symptoms savedSymptom = symptomRepository.save(symptom);
        return mapToSymptomDto(savedSymptom);
    }

    @Override
    @Transactional
    public SymptomDto updateSymptom(Integer id, SymptomDto symptomDto) {
        Optional<Symptoms> symptomOpt = symptomRepository.findById(id);
        if (symptomOpt.isEmpty()) {
            throw new IllegalArgumentException("Symptom with ID " + id + " not found");
        }

        Symptoms symptom = symptomOpt.get();

        // Update specialization if provided
        if (symptomDto.getSpecializationId() != null || symptomDto.getSpecializationName() != null) {
            Specialization specialization;
            if (symptomDto.getSpecializationId() != null) {
                Optional<Specialization> specializationOpt = specializationRepository.findById(symptomDto.getSpecializationId());
                specialization = specializationOpt.orElseGet(() -> createNewSpecialization(symptomDto));
            } else {
                specialization = createNewSpecialization(symptomDto);
            }
            symptom.setSpecialization(specialization);
        }

        // Update other fields
        if (symptomDto.getName() != null) {
            symptom.setName(symptomDto.getName());
        }
        if (symptomDto.getDescription() != null) {
            symptom.setDescription(symptomDto.getDescription());
        }

        Symptoms updatedSymptom = symptomRepository.save(symptom);
        return mapToSymptomDto(updatedSymptom);
    }

    @Transactional
    @Override
    public void deleteSymptom(Integer id) {
        Optional<Symptoms> symptomOpt = symptomRepository.findById(id);
        if (symptomOpt.isEmpty()) {
            throw new IllegalArgumentException("Symptom with ID " + id + " not found");
        }

        Symptoms symptom = symptomOpt.get();
        symptom.setDeletedAt(new Date());
        symptomRepository.save(symptom);
    }

    @Override
    public List<SymptomWithSpecializationResponse> getSymptomsWithSpecializations() {
        List<Specialization> specializations = specializationRepository.findAll();
        List<SymptomWithSpecializationResponse> response = new ArrayList<>();

        for (Specialization specialization : specializations) {
            List<Symptoms> symptoms = symptomRepository.findBySpecializationId(specialization.getId());
            if (!symptoms.isEmpty()) {
                SymptomWithSpecializationResponse specResponse = new SymptomWithSpecializationResponse();
                specResponse.setSpecialistId(specialization.getId());
                specResponse.setSpecialist(specialization.getName());

                List<SymptomWithSpecializationResponse.SymptomDetail> symptomDetails = symptoms.stream()
                        .map(symptom -> {
                            SymptomWithSpecializationResponse.SymptomDetail detail = new SymptomWithSpecializationResponse.SymptomDetail();
                            detail.setSymptomId(symptom.getId());
                            detail.setSymptom(symptom.getName());
                            detail.setImageUrl(generateImageUrl(symptom.getId()));
                            return detail;
                        })
                        .collect(Collectors.toList());

                specResponse.setSymptom(symptomDetails);
                response.add(specResponse);
            }
        }

        return response;
    }

    private Specialization createNewSpecialization(SymptomDto symptomDto) {
        Specialization specialization = new Specialization();
        specialization.setName(symptomDto.getSpecializationName() != null ?
                symptomDto.getSpecializationName() : "New Specialization");
        specialization.setDescription("Auto-created specialization");
        return specializationRepository.save(specialization);
    }

    private SymptomDto mapToSymptomDto(Symptoms symptom) {
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

    private String generateImageUrl(Integer symptomId) {
        return "https://www.mfine.co/consult/proxy/ast/ga/attachments/downloadFromDb?fileName=Symptoms/default.png";
    }
}