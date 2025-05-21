package com.example.sun_hopital_back_end.repository;

import com.example.sun_hopital_back_end.entity.Symptoms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomRepository extends JpaRepository<Symptoms, Integer> {
    List<Symptoms> findBySpecializationId(Integer specializationId);
}