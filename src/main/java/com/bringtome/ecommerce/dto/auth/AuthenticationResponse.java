package com.bringtome.ecommerce.dto.auth;

import com.bringtome.ecommerce.dto.user.UserResponse;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private UserResponse user;
    private String token;
}
