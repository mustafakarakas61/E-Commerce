package com.bringtome.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @NotBlank(message = "Captcha alanını doldurunuz.")
    private String captcha;

    @NotBlank(message = "Lütfen adınızı giriniz.")
    private String firstName;

    @NotBlank(message = "Lütfen soyadınızı giriniz.")
    private String lastName;

    @Size(min = 6, max = 16, message = "Parola 6 ila 16 karakter uzunluğunda olmalıdır.")
    private String password;

    @Size(min = 6, max = 16, message = "Parola onayı 6 ila 16 karakter uzunluğunda olmalıdır.")
    private String password2;

    @Email(message = "Geçersiz email")
    @NotBlank(message = "Lütfen email alanını doldurunuz.")
    private String email;
}
