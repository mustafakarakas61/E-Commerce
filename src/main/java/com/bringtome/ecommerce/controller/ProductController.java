package com.bringtome.ecommerce.controller;

import com.bringtome.ecommerce.dto.GraphQLRequest;
import com.bringtome.ecommerce.dto.HeaderResponse;
import com.bringtome.ecommerce.dto.product.AllProductResponse;
import com.bringtome.ecommerce.dto.product.ProductResponse;
import com.bringtome.ecommerce.dto.product.ProductSearchRequest;
import com.bringtome.ecommerce.dto.product.SearchTypeRequest;
import com.bringtome.ecommerce.dto.review.ReviewResponse;
import com.bringtome.ecommerce.mapper.ProductMapper;
import com.bringtome.ecommerce.service.Impl.MyPageable;
import com.bringtome.ecommerce.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final GraphQLProvider graphQLProvider;

    @Autowired
    public ProductController(ProductMapper productMapper, GraphQLProvider graphQLProvider) {
        this.productMapper = productMapper;
        this.graphQLProvider = graphQLProvider;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(@PageableDefault(size = 15) MyPageable pageable) {
        HeaderResponse<ProductResponse> response = productMapper.getAllProducts(pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<AllProductResponse> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productMapper.getProductById(productId));
    }

    @GetMapping("/reviews/{productId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(productMapper.getReviewsByProductId(productId));
    }

    @PostMapping("/ids")
    public ResponseEntity<List<ProductResponse>> getProductsByIds(@RequestBody List<Long> productsIds) {
        return ResponseEntity.ok(productMapper.getProductsByIds(productsIds));
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProductResponse>> findProductsByFilterParams(@RequestBody ProductSearchRequest filter,
                                                                            @PageableDefault(size = 15) Pageable pageable) {
        HeaderResponse<ProductResponse> response = productMapper.findProductsByFilterParams(filter, pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @PostMapping("/search/gender")
    public ResponseEntity<List<ProductResponse>> findByProductGender(@RequestBody ProductSearchRequest filter) {
        return ResponseEntity.ok(productMapper.findByProductType(filter.getProductType()));
    }

    @PostMapping("/search/product")
    public ResponseEntity<List<ProductResponse>> findByProducer(@RequestBody ProductSearchRequest filter) {
        return ResponseEntity.ok(productMapper.findByProducer(filter.getProducer()));
    }

    @PostMapping("/search/text")
    public ResponseEntity<List<ProductResponse>> findByInputText(@RequestBody SearchTypeRequest searchType,
                                                                 @PageableDefault(size = 15) Pageable pageable) {
        HeaderResponse<ProductResponse> response = productMapper.findByInputText(searchType.getSearchType(), searchType.getText(), pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @PostMapping("/graphql/ids")
    public ResponseEntity<ExecutionResult> getProductsByIdsQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/products")
    public ResponseEntity<ExecutionResult> getAllProductsByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/product")
    public ResponseEntity<ExecutionResult> getProductByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }
}
