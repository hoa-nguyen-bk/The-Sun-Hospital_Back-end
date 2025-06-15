package com.example.sun_hopital_back_end.repository;

import com.example.sun_hopital_back_end.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    Optional<Specialization> findById(Integer id);

}