package com.bringtome.ecommerce.service;

import com.bringtome.ecommerce.entity.ProductEntity;
import com.bringtome.ecommerce.entity.ReviewEntity;
import com.bringtome.ecommerce.entity.UserEntity;
import graphql.schema.DataFetcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserEntity getUserById(Long userId);

    UserEntity getUserInfo(String email);
    
    Page<UserEntity> getAllUsers(Pageable pageable);

    List<ProductEntity> getCart(List<Long> prodcutIds);

    UserEntity updateUserInfo(String email, UserEntity user);

    ReviewEntity addReviewToProduct(ReviewEntity review, Long productId);

    DataFetcher<List<UserEntity>> getAllUsersByQuery();

    DataFetcher<UserEntity> getUserByQuery();
}
