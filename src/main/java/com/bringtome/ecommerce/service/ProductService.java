package com.bringtome.ecommerce.service;

import com.bringtome.ecommerce.entity.ProductEntity;
import com.bringtome.ecommerce.entity.ReviewEntity;
import com.bringtome.ecommerce.enums.SearchProductEnum;
import com.bringtome.ecommerce.repository.projection.ProductProjection;
import graphql.schema.DataFetcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductEntity getProductById(Long productId);

    Page<ProductProjection> getAllProducts(Pageable pageable);

    List<ProductProjection> getProductsByIds(List<Long> productsId);

    Page<ProductProjection> findProductsByFilterParams(List<String> producers, List<String> genders, List<Integer> prices,
                                                       boolean sortByPrice, Pageable pageable);

    List<ProductEntity> findByProducer(String producer);

    List<ProductEntity> findByProductType(String productType);

    Page<ProductProjection> findByInputText(SearchProductEnum searchType, String text, Pageable pageable);

    ProductEntity saveProduct(ProductEntity product, MultipartFile file);

    String deleteProduct(Long productId);

    List<ReviewEntity> getReviewsByProductId(Long productId);

    DataFetcher<ProductEntity> getProductByQuery();

    DataFetcher<List<ProductProjection>> getAllProductsByQuery();

    DataFetcher<List<ProductEntity>> getAllProductsByIdsQuery();
}
