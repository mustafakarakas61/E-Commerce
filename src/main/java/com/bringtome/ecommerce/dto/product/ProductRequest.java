package com.bringtome.ecommerce.dto.product;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductRequest {

    private Long id;

    @NotBlank(message = "Boş bırakılamaz.")
    @Length(max = 255)
    private String city;

    private String filename;

    @NotBlank(message = "Boş bırakılamaz.")
    @Length(max = 255)
    private String productTitle;

    @NotBlank(message = "Boş bırakılamaz.")
    @Length(max = 255)
    private String producer;

    @NotBlank(message = "Boş bırakılamaz.")
    @Length(max = 255)
    private String colors;

   // @NotBlank(message = "Boş bırakılamaz.")
    @Length(max = 255)
    private String product_type;

    @NotNull(message = "Boş bırakılamaz.")
    private Integer price;

    @NotBlank(message = "Boş bırakılamaz.")
    @Length(max = 255)
    private String type;

    @NotNull(message = "Boş bırakılamaz.")
    private Integer year;
}
