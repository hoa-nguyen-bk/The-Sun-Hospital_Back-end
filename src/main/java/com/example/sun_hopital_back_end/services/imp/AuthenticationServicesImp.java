package com.example.sun_hopital_back_end.services.imp;


import com.example.sun_hopital_back_end.entity.Patient;
import com.example.sun_hopital_back_end.repository.PatientRepository;
import com.example.sun_hopital_back_end.services.AuthenticationServices;
import com.example.sun_hopital_back_end.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServicesImp implements AuthenticationServices {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public String authenticate(String email, String password) {
        System.out.println("Received login request:");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        String token = "";

        Optional<Patient> patientOptional = patientRepository.findByEmail(email);

        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            if (passwordEncoder.matches(password, patient.getPassword())) {
                token = jwtUtils.generateToken("Hello");
            }
        }
        return token;
    }


}