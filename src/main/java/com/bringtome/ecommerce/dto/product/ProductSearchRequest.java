package com.bringtome.ecommerce.dto.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductSearchRequest {
    private List<String> producers;
    private List<String> types;
    private List<Integer> prices;
    private Boolean sortByPrice;
    private String producer;
    private String productType;
}
