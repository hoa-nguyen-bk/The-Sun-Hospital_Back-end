package com.example.sun_hopital_back_end.services.imp;

import com.example.sun_hopital_back_end.dto.SignUpDto;
import com.example.sun_hopital_back_end.entity.Role;
import com.example.sun_hopital_back_end.entity.User;
import com.example.sun_hopital_back_end.repository.RoleRepository;
import com.example.sun_hopital_back_end.repository.UserRepository;
import com.example.sun_hopital_back_end.services.AuthenticationServices;
import com.example.sun_hopital_back_end.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.sun_hopital_back_end.payload.response.BaseResponse;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationServicesImp implements AuthenticationServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String authenticate(String email, String password) {
        System.out.println("Received login request:");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        String token = "";
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                token = jwtUtils.generateToken("Hello");
            }
        }
        return token;
    }


    @Override
    public BaseResponse registerUser(SignUpDto signUpDto) {
        BaseResponse response = new BaseResponse();

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            response.setCode(400);
            response.setMessage("Username is already taken!");
            return response;
        }

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            response.setCode(400);
            response.setMessage("Email is already taken!");
            return response;
        }

        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);
        userRepository.save(user);

        response.setCode(200);
        response.setMessage("User registered successfully");
        return response;
    }
}
