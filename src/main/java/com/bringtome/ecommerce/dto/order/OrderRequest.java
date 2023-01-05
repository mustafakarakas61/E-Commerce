package com.bringtome.ecommerce.dto.order;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class OrderRequest {

    private Double totalPrice;
    private Map<Long, Long> productsId;

    @NotBlank(message = "Boş bırakılamaz.")
    private String firstName;

    @NotBlank(message = "Boş bırakılamaz.")
    private String lastName;

    @NotBlank(message = "Boş bırakılamaz.")
    private String city;

    @NotBlank(message = "Boş bırakılamaz.")
    private String address;

    @Email(message = "Geçersiz mail")
    @NotBlank(message = "Mail alanı boş bırakılamaz.")
    private String email;

    @NotBlank(message = "Telefon numarası boş bırakılamaz.")
    private String phoneNumber;

    @NotNull(message = "Posta kodu boş bırakılamaz.")
    @Min(value = 5, message = "Posta kodu dizini en az 5 basamak içermelidir.")
    private Integer postIndex;
}
