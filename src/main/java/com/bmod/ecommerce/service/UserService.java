package com.bmod.ecommerce.service;

import com.bmod.ecommerce.entity.PerfumeEntity;
import com.bmod.ecommerce.entity.ReviewEntity;
import com.bmod.ecommerce.entity.UserEntity;
import graphql.schema.DataFetcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserEntity getUserById(Long userId);

    UserEntity getUserInfo(String email);
    
    Page<UserEntity> getAllUsers(Pageable pageable);

    List<PerfumeEntity> getCart(List<Long> perfumeIds);

    UserEntity updateUserInfo(String email, UserEntity user);

    ReviewEntity addReviewToPerfume(ReviewEntity review, Long perfumeId);

    DataFetcher<List<UserEntity>> getAllUsersByQuery();

    DataFetcher<UserEntity> getUserByQuery();
}
