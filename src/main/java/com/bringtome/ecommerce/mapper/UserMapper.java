package com.bringtome.ecommerce.mapper;

import com.bringtome.ecommerce.entity.ReviewEntity;
import com.bringtome.ecommerce.entity.UserEntity;
import com.bringtome.ecommerce.dto.HeaderResponse;
import com.bringtome.ecommerce.dto.product.ProductResponse;
import com.bringtome.ecommerce.dto.review.ReviewRequest;
import com.bringtome.ecommerce.dto.review.ReviewResponse;
import com.bringtome.ecommerce.dto.user.BaseUserResponse;
import com.bringtome.ecommerce.dto.user.UpdateUserRequest;
import com.bringtome.ecommerce.dto.user.UserResponse;
import com.bringtome.ecommerce.exception.InputFieldException;
import com.bringtome.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CommonMapper commonMapper;
    private final UserService userService;

    public UserResponse getUserById(Long userId) {
        return commonMapper.convertToResponse(userService.getUserById(userId), UserResponse.class);
    }

    public UserResponse getUserInfo(String email) {
        return commonMapper.convertToResponse(userService.getUserInfo(email), UserResponse.class);
    }

    public List<ProductResponse> getCart(List<Long> productsIds) {
        return commonMapper.convertToResponseList(userService.getCart(productsIds), ProductResponse.class);
    }

    public HeaderResponse<BaseUserResponse> getAllUsers(PageRequest pageRequest) {
        Page<UserEntity> users = userService.getAllUsers(pageRequest);
        return commonMapper.getHeaderResponse(users.getContent(), users.getTotalPages(), users.getTotalElements(), BaseUserResponse.class);
    }

    public UserResponse updateUserInfo(String email, UpdateUserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        UserEntity user = commonMapper.convertToEntity(userRequest, UserEntity.class);
        return commonMapper.convertToResponse(userService.updateUserInfo(email, user), UserResponse.class);
    }

    public ReviewResponse addReviewToProduct(ReviewRequest reviewRequest, Long productId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        ReviewEntity review = commonMapper.convertToEntity(reviewRequest, ReviewEntity.class);
        return commonMapper.convertToResponse(userService.addReviewToProduct(review, productId), ReviewResponse.class);
    }
}
