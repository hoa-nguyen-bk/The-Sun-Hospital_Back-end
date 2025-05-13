package com.example.sun_hopital_back_end.controller;

import com.example.sun_hopital_back_end.payload.request.LoginRequest;
import com.example.sun_hopital_back_end.payload.request.SignUpRequest;
import com.example.sun_hopital_back_end.payload.response.BaseResponse;
import com.example.sun_hopital_back_end.repository.UserRepository;
import com.example.sun_hopital_back_end.repository.RoleRepository;
import com.example.sun_hopital_back_end.dto.SignUpDto;
import com.example.sun_hopital_back_end.services.AuthenticationServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/authentication")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthenticationServices authenticationServices;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) {
        String token = authenticationServices.authenticate(loginRequest.getEmail(), loginRequest.getPassword()).toString();
        BaseResponse response = new BaseResponse();
        if (token.isEmpty()) {
            response.setCode(401);
            response.setMessage("Email hoặc mật khẩu không đúng");
            return ResponseEntity.status(401).body(response);
        } else {
            response.setCode(200);
            response.setMessage("Đăng nhập thành công");
            response.setData(token);
            return ResponseEntity.ok(response);
        }
    }
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setEmail(request.getEmail());
        signUpDto.setPassword(request.getPassword());
        // Add more fields if you add them later to the request

        BaseResponse response = authenticationServices.registerUser(signUpDto);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
