package com.example.sun_hopital_back_end.payload.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotNull(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Password must be at least 8 characters, contain letters, numbers and special characters"
    )
    private String password;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

//    @Null
//    private String phone;

}
