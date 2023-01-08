package com.bringtome.ecommerce.service;

import com.bringtome.ecommerce.entity.ProductEntity;
import com.bringtome.ecommerce.entity.ReviewEntity;
import com.bringtome.ecommerce.enums.SearchProductEnum;
import com.bringtome.ecommerce.repository.projection.ProductProjection;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
public interface ProductService {

    ProductEntity getProductById(Long productId);

    Page<ProductProjection> getAllProducts(PageRequest pageRequest);

    List<ProductProjection> getProductsByIds(List<Long> productsId);

    Page<ProductProjection> findProductsByFilterParams(List<String> producers, List<String> types, List<Integer> prices,
                                                       boolean sortByPrice, PageRequest pageRequest);

    List<ProductEntity> findByProducer(String producer);

    List<ProductEntity> findByProductType(String productType);

    Page<ProductProjection> findByInputText(SearchProductEnum searchType, String text, PageRequest pageRequest);

    ProductEntity saveProduct(ProductEntity product, MultipartFile file);

    String deleteProduct(Long productId);

    List<ReviewEntity> getReviewsByProductId(Long productId);

    DataFetcher<ProductEntity> getProductByQuery();

    DataFetcher<List<ProductProjection>> getAllProductsByQuery();

    DataFetcher<List<ProductEntity>> getAllProductsByIdsQuery();
}
