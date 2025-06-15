package com.example.sun_hopital_back_end.services;

import com.example.sun_hopital_back_end.entity.User;
import com.example.sun_hopital_back_end.repository.UserRepository;
import com.example.sun_hopital_back_end.services.imp.AuthenticationServicesImp;
import com.example.sun_hopital_back_end.utils.JwtUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServicesImpTest {

    @InjectMocks
    private AuthenticationServicesImp authenticationServices;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Test
    void testAuthenticate_Success() {
        String email = "john.doe@example.com";
        String rawPassword = "patient123";
        String encodedPassword = "$2a$10$1234567890abcd"; // giả định là đúng
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
        Mockito.when(jwtUtils.generateToken("Hello")).thenReturn("fake-jwt-token");

        String result = authenticationServices.authenticate(email, rawPassword);
        Assertions.assertEquals("fake-jwt-token", result);
    }

    @Test
    void testAuthenticate_Failure_InvalidPassword() {
        Mockito.when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(new User()));
        Mockito.when(passwordEncoder.matches(Mockito.any(), Mockito.any()))
                .thenReturn(false);

        String token = authenticationServices.authenticate("test@gmail.com", "wrongpass");
        Assertions.assertTrue(token.isEmpty());
    }

    @Test
    void testAuthenticate_Failure_UserNotFound() {
        Mockito.when(userRepository.findByEmail("john.doe@example.coms"))
                .thenReturn(Optional.empty());

        String token = authenticationServices.authenticate("notfound@gmail.com", "pass");
        Assertions.assertTrue(token.isEmpty());
    }
}
