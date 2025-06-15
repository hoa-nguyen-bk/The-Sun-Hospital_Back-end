package com.example.sun_hopital_back_end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class SymptomDto {
    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private Integer specializationId;

    private String specializationName;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}