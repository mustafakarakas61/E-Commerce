package com.bringtome.ecommerce.service.Impl;

import com.bringtome.ecommerce.entity.ProductEntity;
import com.bringtome.ecommerce.entity.ReviewEntity;
import com.bringtome.ecommerce.enums.SearchProductEnum;
import com.bringtome.ecommerce.exception.ApiRequestException;
import com.bringtome.ecommerce.repository.ProductRepository;
import com.bringtome.ecommerce.repository.projection.ProductProjection;
import com.bringtome.ecommerce.service.ProductService;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:3000")
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public ProductEntity getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ApiRequestException("Ürün bulunamadı.", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<ReviewEntity> getReviewsByProductId(Long productId) {
        ProductEntity product = getProductById(productId);
        return product.getReviews();
    }

    @Override
    public Page<ProductProjection> getAllProducts(Pageable pageable) {
        return productRepository.findAllByOrderByIdAsc(pageable);
    }

    @Override
    public List<ProductProjection> getProductsByIds(List<Long> productsId) {
        return productRepository.getProductsByIds(productsId);
    }

    @Override
    public Page<ProductProjection> findProductsByFilterParams(List<String> producers, List<String> types, List<Integer> prices,
                                                              boolean sortByPrice, Pageable pageable) {
        return productRepository.findProductsByFilterParams(producers, types, prices.get(0), prices.get(1), sortByPrice, pageable);
    }

    @Override
    public List<ProductEntity> findByProducer(String producer) {
        return productRepository.findByProducerOrderByPriceDesc(producer);
    }

    @Override
    public List<ProductEntity> findByProductType(String productType) {
        return productRepository.findByProductTypeOrderByPriceDesc(productType);
    }

    @Override
    public Page<ProductProjection> findByInputText(SearchProductEnum searchType, String text, Pageable pageable) {
        if (searchType.equals(SearchProductEnum.BRAND)) {
            return productRepository.findByProducer(text, pageable);
        } else if (searchType.equals(SearchProductEnum.PRODUCT_TITLE)) {
            return productRepository.findByProductTitle(text, pageable);
        } else {
            return productRepository.findByManufacturerCity(text, pageable);
        }
    }

    @Override
    @Transactional
    public ProductEntity saveProduct(ProductEntity product, MultipartFile multipartFile) {
        if (multipartFile == null) {
        } else {
            File file = new File(multipartFile.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String fileName = UUID.randomUUID().toString() + "." + multipartFile.getOriginalFilename();
            file.delete();
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public String deleteProduct(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ApiRequestException("Ürün bulunamadı.", HttpStatus.NOT_FOUND));
        productRepository.delete(product);
        return "Ürün başarıyla silindi.";
    }

    @Override
    public DataFetcher<ProductEntity> getProductByQuery() {
        return dataFetchingEnvironment -> {
            Long productId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return productRepository.findById(productId).get();
        };
    }

    @Override
    public DataFetcher<List<ProductProjection>> getAllProductsByQuery() {
        return dataFetchingEnvironment -> productRepository.findAllByOrderByIdAsc();
    }

    @Override
    public DataFetcher<List<ProductEntity>> getAllProductsByIdsQuery() {
        return dataFetchingEnvironment -> {
            List<String> objects = dataFetchingEnvironment.getArgument("ids");
            List<Long> productsId = objects.stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            return productRepository.findByIdIn(productsId);
        };
    }
}
