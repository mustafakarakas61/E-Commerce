package com.bringtome.ecommerce.repository;

import com.bringtome.ecommerce.entity.ProductEntity;
import com.bringtome.ecommerce.repository.projection.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductProjection> findAllByOrderByIdAsc();
    
    @Query("SELECT product FROM ProductEntity product ORDER BY product.id ASC")
    Page<ProductProjection> findAllByOrderByIdAsc(Pageable pageable);

    List<ProductEntity> findByProducerOrderByPriceDesc(String producer);

    List<ProductEntity> findByProductTypeOrderByPriceDesc(String productType);

    List<ProductEntity> findByIdIn(List<Long> productsIds);

    @Query("SELECT product FROM ProductEntity product WHERE product.id IN :productsIds")
    List<ProductProjection> getProductsByIds(List<Long> productsIds);

    @Query("SELECT product FROM ProductEntity product " +
            "WHERE (coalesce(:producers, null) IS NULL OR product.producer IN :producers) " +
            "AND (coalesce(:types, null) IS NULL OR product.productType IN :types) " +
            "AND (coalesce(:priceStart, null) IS NULL OR product.price BETWEEN :priceStart AND :priceEnd) " +
            "ORDER BY CASE WHEN :sortByPrice = true THEN product.price ELSE -product.price END ASC")
    Page<ProductProjection> findProductsByFilterParams(
            List<String> producers,
            List<String> types,
            Integer priceStart, 
            Integer priceEnd, 
            boolean sortByPrice,
            Pageable pageable);

    @Query("SELECT product FROM ProductEntity product " +
            "WHERE UPPER(product.producer) LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY product.price DESC")
    Page<ProductProjection> findByProducer(String text, Pageable pageable);

    @Query("SELECT product FROM ProductEntity product " +
            "WHERE UPPER(product.productTitle) LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY product.price DESC")
    Page<ProductProjection> findByProductTitle(String text, Pageable pageable);

    @Query("SELECT product FROM ProductEntity product " +
            "WHERE UPPER(product.city) LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY product.price DESC")
    Page<ProductProjection> findByManufacturerCity(String text, Pageable pageable);
}
