package com.bringtome.ecommerce.dto.user;

import com.bringtome.ecommerce.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BaseUserResponse {
    private Long id;
    private String email;
    private String firstName;
    private Set<RoleEnum> roles;
    private String provider;
}
