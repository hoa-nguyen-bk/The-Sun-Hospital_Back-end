package com.example.sun_hopital_back_end.services.imp;

import com.example.sun_hopital_back_end.dto.SpecializationDto;
import com.example.sun_hopital_back_end.entity.Specialization;
import com.example.sun_hopital_back_end.repository.SpecializationRepository;
import com.example.sun_hopital_back_end.services.SpecializationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecializationServicesImp implements SpecializationServices {
    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public List<SpecializationDto> getAllSpecializations(Integer pageNumber, Integer pageSize) {
        List<Specialization> specializations;
        if (pageSize == null) {
            specializations = specializationRepository.findAll();
        } else {
            int page = (pageNumber != null && pageNumber >= 0) ? pageNumber : 0;
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<Specialization> specializationPage = specializationRepository.findAll(pageable);
            specializations = specializationPage.getContent();
        }

        return specializations.stream()
                .map(this::mapToSpecializationDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SpecializationDto createSpecialization(SpecializationDto specializationDto) {
        Specialization specialization = new Specialization();
        specialization.setName(specializationDto.getName());
        specialization.setDescription(specializationDto.getDescription());

        Specialization savedSpecialization = specializationRepository.save(specialization);
        return mapToSpecializationDto(savedSpecialization);
    }

    @Override
    @Transactional
    public SpecializationDto updateSpecialization(Integer id, SpecializationDto specializationDto) {
        Optional<Specialization> specializationOpt = specializationRepository.findById(id);
        if (specializationOpt.isEmpty()) {
            throw new IllegalArgumentException("Specialization with ID " + id + " not found");
        }

        Specialization specialization = specializationOpt.get();
        if (specializationDto.getName() != null) {
            specialization.setName(specializationDto.getName());
        }
        if (specializationDto.getDescription() != null) {
            specialization.setDescription(specializationDto.getDescription());
        }

        Specialization updatedSpecialization = specializationRepository.save(specialization);
        return mapToSpecializationDto(updatedSpecialization);
    }

    @Override
    @Transactional
    public void deleteSpecialization(Integer id) {
        Optional<Specialization> specializationOpt = specializationRepository.findById(id);
        if (specializationOpt.isEmpty()) {
            throw new IllegalArgumentException("Specialization with ID " + id + " not found");
        }

        Specialization specialization = specializationOpt.get();
        specialization.setDeletedAt(new Date());
        specializationRepository.save(specialization);
    }

    private SpecializationDto mapToSpecializationDto(Specialization specialization) {
        SpecializationDto specializationDto = new SpecializationDto();
        specializationDto.setId(specialization.getId());
        specializationDto.setName(specialization.getName());
        specializationDto.setDescription(specialization.getDescription());
        specializationDto.setCreatedAt(specialization.getCreatedAt());
        specializationDto.setUpdatedAt(specialization.getUpdatedAt());
        specializationDto.setDeletedAt(specialization.getDeletedAt());
        return specializationDto;
    }
}