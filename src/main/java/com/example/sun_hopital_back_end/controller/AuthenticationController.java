package com.example.sun_hopital_back_end.controller;//package com.example.sun_hopital_back_end.controller;
//
//import com.example.sun_hopital_back_end.payload.response.BaseResponse;
//import com.example.sun_hopital_back_end.services.AuthenticationServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/authentication")
//@CrossOrigin(origins = "http://localhost:4200")
//public class AuthenticationController {
//
//    @Autowired
//    private AuthenticationServices authenticationServices;
//
//    @PostMapping("/login")
//
//    public ResponseEntity<?> signIn(@RequestParam String email, @RequestParam String password) {
//        boolean isSuccess = authenticationServices.authenticate(email, password);
//
//        BaseResponse response = new BaseResponse();
//        response.setData(isSuccess);
//        response.setMessage(isSuccess ? "Success !" : "Failure");
//        response.setCode(isSuccess ? 0 : 1);
//
//        return ResponseEntity.ok(response);
//    }
//
//}

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.springframework.web.bind.annotation.*; // Import đầy đủ annotation
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.sun_hopital_back_end.payload.response.BaseResponse;
import com.example.sun_hopital_back_end.payload.request.LoginRequest;
import com.example.sun_hopital_back_end.services.AuthenticationServices;

import javax.crypto.SecretKey;

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
}
