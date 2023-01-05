package com.bringtome.ecommerce.dto.product;

import com.bringtome.ecommerce.enums.SearchProductEnum;
import lombok.Data;

@Data
public class SearchTypeRequest {
    private SearchProductEnum searchType;
    private String text;
}
