package com.bringtome.ecommerce.controller;

import com.bringtome.ecommerce.dto.GraphQLRequest;
import com.bringtome.ecommerce.dto.HeaderResponse;
import com.bringtome.ecommerce.dto.order.OrderResponse;
import com.bringtome.ecommerce.dto.product.AllProductResponse;
import com.bringtome.ecommerce.dto.product.ProductRequest;
import com.bringtome.ecommerce.dto.user.BaseUserResponse;
import com.bringtome.ecommerce.dto.user.UserResponse;
import com.bringtome.ecommerce.mapper.OrderMapper;
import com.bringtome.ecommerce.mapper.ProductMapper;
import com.bringtome.ecommerce.mapper.UserMapper;
import com.bringtome.ecommerce.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserMapper userMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final GraphQLProvider graphQLProvider;

    @PostMapping("/add")
    public ResponseEntity<AllProductResponse> addProduct(@RequestPart(name = "file", required = false) MultipartFile file,
                                                         @RequestPart("product") @Valid ProductRequest product,
                                                         BindingResult bindingResult) {
        return ResponseEntity.ok(productMapper.saveProduct(product, file, bindingResult));
    }

    @PostMapping("/edit")
    public ResponseEntity<AllProductResponse> updateProduct(@RequestPart(name = "file", required = false) MultipartFile file,
                                                            @RequestPart("product") @Valid ProductRequest product,
                                                            BindingResult bindingResult) {
        return ResponseEntity.ok(productMapper.saveProduct(product, file, bindingResult));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productMapper.deleteProduct(productId));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<OrderResponse> response = orderMapper.getAllOrders(pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @GetMapping("/order/{userEmail}")
    public ResponseEntity<List<OrderResponse>> getUserOrdersByEmail(@PathVariable String userEmail, 
                                                                    @PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<OrderResponse> response = orderMapper.getUserOrders(userEmail, pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @DeleteMapping("/order/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.deleteOrder(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userMapper.getUserById(userId));
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<BaseUserResponse>> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
        HeaderResponse<BaseUserResponse> response = userMapper.getAllUsers(pageable);
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @PostMapping("/graphql/user")
    public ResponseEntity<ExecutionResult> getUserByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/user/all")
    public ResponseEntity<ExecutionResult> getAllUsersByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/orders")
    public ResponseEntity<ExecutionResult> getAllOrdersQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/order")
    public ResponseEntity<ExecutionResult> getUserOrdersByEmailQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }
}
