package com.bringtome.ecommerce.dto.review;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReviewRequest {

    private Long productId;

    @NotBlank(message = "Boş bırakılamaz.")
    private String author;

    @NotBlank(message = "Boş bırakılamaz.")
    private String message;

    @NotNull(message = "Puanınızı verin")
    private Integer rating;
}
