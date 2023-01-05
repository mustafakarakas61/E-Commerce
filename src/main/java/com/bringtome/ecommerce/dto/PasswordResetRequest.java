package com.bringtome.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PasswordResetRequest {

    private String email;

    @Size(min = 6, max = 16, message = "Parola 6 ile 16 karakter uzunluğunda olmalıdır")
    private String password;

    @Size(min = 6, max = 16, message = "Parola onayı 6 ila 16 karakter uzunluğunda olmalıdır")
    private String password2;
}
