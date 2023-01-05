package com.bringtome.ecommerce.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String filename;
    private String productTitle;
    private String producer;
    private Integer price;
    private Double productRating;
    private Integer reviewsCount;
}
