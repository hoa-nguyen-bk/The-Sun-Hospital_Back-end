package com.example.sun_hopital_back_end.controller;

import com.example.sun_hopital_back_end.payload.request.SignUpRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*; // Import đầy đủ annotation
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.sun_hopital_back_end.payload.response.BaseResponse;
import com.example.sun_hopital_back_end.payload.request.LoginRequest;
import com.example.sun_hopital_back_end.services.AuthenticationServices;

import javax.crypto.SecretKey;
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

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest) { // Đổi sang @RequestBody
        SecretKey key = Jwts.SIG.HS256.key().build();
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Key: " + secretString);
        String token = authenticationServices.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        BaseResponse response = new BaseResponse();
        response.setData(token);
        response.setMessage("");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Map<String, String>> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("field", error.getField());
                errorMap.put("message", error.getDefaultMessage());
                errors.add(errorMap);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("code", 400);
            response.put("message", "Validation failed");
            response.put("errors", errors);

            return ResponseEntity.status(400).body(response);
        }

        BaseResponse response = new BaseResponse();
        response.setData(request);
        return ResponseEntity.ok(response);
    }

}
