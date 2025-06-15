package com.example.sun_hopital_back_end.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class SpecializationDto {
    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}