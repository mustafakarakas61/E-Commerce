package com.bmod.ecommerce.dto.auth;

import com.bmod.ecommerce.dto.user.UserResponse;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private UserResponse user;
    private String token;
}
