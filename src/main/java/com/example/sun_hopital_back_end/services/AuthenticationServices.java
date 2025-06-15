package com.example.sun_hopital_back_end.services;

import com.example.sun_hopital_back_end.dto.SignUpDto;
import com.example.sun_hopital_back_end.payload.response.BaseResponse;

public interface AuthenticationServices {
    String authenticate(String email, String password);
    BaseResponse registerUser(SignUpDto signUpDto);
}
