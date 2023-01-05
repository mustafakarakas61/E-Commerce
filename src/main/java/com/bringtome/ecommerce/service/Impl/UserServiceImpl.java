package com.bringtome.ecommerce.service.Impl;

import com.bringtome.ecommerce.entity.ProductEntity;
import com.bringtome.ecommerce.entity.ReviewEntity;
import com.bringtome.ecommerce.entity.UserEntity;
import com.bringtome.ecommerce.exception.ApiRequestException;
import com.bringtome.ecommerce.repository.ProductRepository;
import com.bringtome.ecommerce.repository.ReviewRepository;
import com.bringtome.ecommerce.repository.UserRepository;
import com.bringtome.ecommerce.service.UserService;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException("User not found.", HttpStatus.NOT_FOUND));
    }

    @Override
    public UserEntity getUserInfo(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Email not found.", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<UserEntity> getAllUsers(Pageable pageable) {
        return userRepository.findAllByOrderByIdAsc(pageable);
    }

    @Override
    public List<ProductEntity> getCart(List<Long> productIds) {
        return productRepository.findByIdIn(productIds);
    }

    @Override
    @Transactional
    public UserEntity updateUserInfo(String email, UserEntity user) {
        UserEntity userFromDb = userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Email not found.", HttpStatus.NOT_FOUND));
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setCity(user.getCity());
        userFromDb.setAddress(user.getAddress());
        userFromDb.setPhoneNumber(user.getPhoneNumber());
        userFromDb.setPostIndex(user.getPostIndex());
        return userFromDb;
    }

    @Override
    @Transactional
    public ReviewEntity addReviewToProduct(ReviewEntity review, Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ApiRequestException("Product not found.", HttpStatus.NOT_FOUND));
        List<ReviewEntity> reviews = product.getReviews();
        reviews.add(review);
        double totalReviews = reviews.size();
        double sumRating = reviews.stream().mapToInt(ReviewEntity::getRating).sum();
        product.setProductRating(sumRating / totalReviews);
        return reviewRepository.save(review);
    }
    
    @Override
    public DataFetcher<UserEntity> getUserByQuery() {
        return dataFetchingEnvironment -> {
            Long userId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return userRepository.findById(userId).get();
        };
    }

    @Override
    public DataFetcher<List<UserEntity>> getAllUsersByQuery() {
        return dataFetchingEnvironment -> userRepository.findAllByOrderByIdAsc();
    }
}
