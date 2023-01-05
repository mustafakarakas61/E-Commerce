package com.bringtome.ecommerce.dto.order;

import com.bringtome.ecommerce.dto.product.ProductResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    private Long id;
    private Long amount;
    private Long quantity;
    private ProductResponse product;
}
