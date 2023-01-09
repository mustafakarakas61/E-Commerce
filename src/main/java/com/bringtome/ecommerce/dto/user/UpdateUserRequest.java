package com.bringtome.ecommerce.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserRequest {
    private Long id;

    private String email;

    @NotBlank(message = "Lütfen adınızı giriniz.")
    private String firstName;

    @NotBlank(message = "Lütfen soyadınızı giriniz.")
    private String lastName;

    private String city;
    private String address;
    private String phoneNumber;
    private String postIndex;
}
