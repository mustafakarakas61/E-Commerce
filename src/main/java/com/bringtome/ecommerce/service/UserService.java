package com.bringtome.ecommerce.service;

import com.bringtome.ecommerce.entity.ProductEntity;
import com.bringtome.ecommerce.entity.ReviewEntity;
import com.bringtome.ecommerce.entity.UserEntity;
import graphql.schema.DataFetcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
public interface UserService {

    UserEntity getUserById(Long userId);

    UserEntity getUserInfo(String email);
    
    Page<UserEntity> getAllUsers(PageRequest pageRequest);

    List<ProductEntity> getCart(List<Long> prodcutIds);

    UserEntity updateUserInfo(String email, UserEntity user);

    ReviewEntity addReviewToProduct(ReviewEntity review, Long productId);

    DataFetcher<List<UserEntity>> getAllUsersByQuery();

    DataFetcher<UserEntity> getUserByQuery();
}
