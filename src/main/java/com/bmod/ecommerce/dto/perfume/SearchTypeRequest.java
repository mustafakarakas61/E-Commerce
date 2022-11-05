package com.bmod.ecommerce.dto.perfume;

import com.bmod.ecommerce.enums.SearchPerfumeEnum;
import lombok.Data;

@Data
public class SearchTypeRequest {
    private SearchPerfumeEnum searchType;
    private String text;
}
