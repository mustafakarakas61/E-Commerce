package com.bringtome.ecommerce.mapper;

import com.bringtome.ecommerce.dto.product.ProductResponse;
import com.bringtome.ecommerce.dto.product.ProductSearchRequest;
import com.bringtome.ecommerce.entity.ProductEntity;
import com.bringtome.ecommerce.dto.HeaderResponse;
import com.bringtome.ecommerce.dto.review.ReviewResponse;
import com.bringtome.ecommerce.enums.SearchProductEnum;
import com.bringtome.ecommerce.exception.InputFieldException;
import com.bringtome.ecommerce.repository.projection.ProductProjection;
import com.bringtome.ecommerce.service.ProductService;
import com.bringtome.ecommerce.dto.product.AllProductResponse;
import com.bringtome.ecommerce.dto.product.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CommonMapper commonMapper;
    private final ProductService productService;

    public AllProductResponse getProductById(Long productId) {
        return commonMapper.convertToResponse(productService.getProductById(productId), AllProductResponse.class);
    }

    public List<ReviewResponse> getReviewsByProductId(Long productId) {
        return commonMapper.convertToResponseList(productService.getReviewsByProductId(productId), ReviewResponse.class);
    }

    public List<ProductResponse> getProductsByIds(List<Long> productsId) {
        return commonMapper.convertToResponseList(productService.getProductsByIds(productsId), ProductResponse.class);
    }

    public HeaderResponse<ProductResponse> getAllProducts(Pageable pageable) {
        Page<ProductProjection> products = productService.getAllProducts(pageable);
        //products.getTotalElements();
        return commonMapper.getHeaderResponse(products.getContent(), products.getTotalPages(), products.getTotalElements(), ProductResponse.class);
    }

    public HeaderResponse<ProductResponse> findProductsByFilterParams(ProductSearchRequest filter, Pageable pageable) {
        Page<ProductProjection> products = productService.findProductsByFilterParams(filter.getProducers(), filter.getTypes(),
                filter.getPrices(), filter.getSortByPrice(), pageable);
        return commonMapper.getHeaderResponse(products.getContent(), products.getTotalPages(), products.getTotalElements(), ProductResponse.class);
    }

    public List<ProductResponse> findByProducer(String producer) {
        return commonMapper.convertToResponseList(productService.findByProducer(producer), ProductResponse.class);
    }

    public List<ProductResponse> findByProductType(String productType) {
        return commonMapper.convertToResponseList(productService.findByProductType(productType), ProductResponse.class);
    }
    
    public HeaderResponse<ProductResponse> findByInputText(SearchProductEnum searchType, String text, Pageable pageable) {
        Page<ProductProjection> products = productService.findByInputText(searchType, text, pageable);
        return commonMapper.getHeaderResponse(products.getContent(), products.getTotalPages(), products.getTotalElements(), ProductResponse.class);
    }

    public AllProductResponse saveProduct(ProductRequest productRequest, MultipartFile file, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        ProductEntity product = commonMapper.convertToEntity(productRequest, ProductEntity.class);
        return commonMapper.convertToResponse(productService.saveProduct(product, file), AllProductResponse.class);
    }

    public String deleteProduct(Long productId) {
        return productService.deleteProduct(productId);
    }
}
