package com.bmod.ecommerce.mapper;

import com.bmod.ecommerce.entity.ReviewEntity;
import com.bmod.ecommerce.entity.UserEntity;
import com.bmod.ecommerce.dto.HeaderResponse;
import com.bmod.ecommerce.dto.perfume.PerfumeResponse;
import com.bmod.ecommerce.dto.review.ReviewRequest;
import com.bmod.ecommerce.dto.review.ReviewResponse;
import com.bmod.ecommerce.dto.user.BaseUserResponse;
import com.bmod.ecommerce.dto.user.UpdateUserRequest;
import com.bmod.ecommerce.dto.user.UserResponse;
import com.bmod.ecommerce.exception.InputFieldException;
import com.bmod.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

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

    public List<PerfumeResponse> getCart(List<Long> perfumesIds) {
        return commonMapper.convertToResponseList(userService.getCart(perfumesIds), PerfumeResponse.class);
    }

    public HeaderResponse<BaseUserResponse> getAllUsers(Pageable pageable) {
        Page<UserEntity> users = userService.getAllUsers(pageable);
        return commonMapper.getHeaderResponse(users.getContent(), users.getTotalPages(), users.getTotalElements(), BaseUserResponse.class);
    }

    public UserResponse updateUserInfo(String email, UpdateUserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        UserEntity user = commonMapper.convertToEntity(userRequest, UserEntity.class);
        return commonMapper.convertToResponse(userService.updateUserInfo(email, user), UserResponse.class);
    }

    public ReviewResponse addReviewToPerfume(ReviewRequest reviewRequest, Long perfumeId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        ReviewEntity review = commonMapper.convertToEntity(reviewRequest, ReviewEntity.class);
        return commonMapper.convertToResponse(userService.addReviewToPerfume(review, perfumeId), ReviewResponse.class);
    }
}
