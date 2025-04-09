package com.example.sun_hopital_back_end.services.imp;

import com.example.sun_hopital_back_end.entity.User;
import com.example.sun_hopital_back_end.repository.UserRepository;
import com.example.sun_hopital_back_end.services.AuthenticationServices;
import com.example.sun_hopital_back_end.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServicesImp implements AuthenticationServices {
    @Autowired
    private UserRepository userRepository;
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
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                token = jwtUtils.generateToken("Hello");
            }
        }
        return token;
    }

}
