package com.bringtome.ecommerce.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AllProductResponse extends ProductResponse {
    private String city;
    private String description;
    private MultipartFile file;
    private String colors;
    private String productType;
    private String type;
    private Integer year;
}
