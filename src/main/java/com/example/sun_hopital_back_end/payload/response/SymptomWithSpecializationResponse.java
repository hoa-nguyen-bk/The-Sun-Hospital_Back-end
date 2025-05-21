package com.example.sun_hopital_back_end.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class SymptomWithSpecializationResponse {
    private Integer specialistId;
    private String specialist;
    private List<SymptomDetail> symptom;

    @Data
    public static class SymptomDetail {
        private Integer symptomId;
        private String symptom;
        private String imageUrl;
    }
}