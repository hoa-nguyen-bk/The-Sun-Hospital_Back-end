package com.example.sun_hopital_back_end.controller;

import com.example.sun_hopital_back_end.payload.request.LoginRequest;
import com.example.sun_hopital_back_end.payload.response.BaseResponse;
import com.example.sun_hopital_back_end.services.AuthenticationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthenticationServices authenticationServices;

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

}
