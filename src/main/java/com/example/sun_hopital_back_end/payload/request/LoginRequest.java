package com.example.sun_hopital_back_end.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
