package com.bringtome.ecommerce.controller;

import com.bringtome.ecommerce.dto.GraphQLRequest;
import com.bringtome.ecommerce.dto.HeaderResponse;
import com.bringtome.ecommerce.dto.order.OrderItemResponse;
import com.bringtome.ecommerce.dto.order.OrderRequest;
import com.bringtome.ecommerce.dto.order.OrderResponse;
import com.bringtome.ecommerce.dto.product.ProductResponse;
import com.bringtome.ecommerce.dto.review.ReviewRequest;
import com.bringtome.ecommerce.dto.review.ReviewResponse;
import com.bringtome.ecommerce.dto.user.UpdateUserRequest;
import com.bringtome.ecommerce.dto.user.UserResponse;
import com.bringtome.ecommerce.mapper.OrderMapper;
import com.bringtome.ecommerce.mapper.UserMapper;
import com.bringtome.ecommerce.model.PrmSearch;
import com.bringtome.ecommerce.security.UserPrincipal;
import com.bringtome.ecommerce.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final GraphQLProvider graphQLProvider;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public UserController(UserMapper userMapper, OrderMapper orderMapper, GraphQLProvider graphQLProvider, SimpMessagingTemplate messagingTemplate) {
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
        this.graphQLProvider = graphQLProvider;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getUserInfo(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(userMapper.getUserInfo(user.getEmail()));
    }

    @PutMapping("/edit")
    public ResponseEntity<UserResponse> updateUserInfo(@AuthenticationPrincipal UserPrincipal user,
                                                       @Valid @RequestBody UpdateUserRequest request,
                                                       BindingResult bindingResult) {
        return ResponseEntity.ok(userMapper.updateUserInfo(user.getEmail(), request, bindingResult));
    }

    @PostMapping("/cart")
    public ResponseEntity<List<ProductResponse>> getCart(@RequestBody List<Long> productsIds) {
        return ResponseEntity.ok(userMapper.getCart(productsIds));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.getOrderById(orderId));
    }

    @GetMapping("/order/{orderId}/items")
    public ResponseEntity<List<OrderItemResponse>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderMapper.getOrderItemsByOrderId(orderId));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@AuthenticationPrincipal UserPrincipal user,
                                                             PrmSearch prmSearch) {
        HeaderResponse<OrderResponse> response = orderMapper.getUserOrders(user.getEmail(), PageRequest.of(0,15));
        return ResponseEntity.ok().headers(response.getHeaders()).body(response.getItems());
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> postOrder(@Valid @RequestBody OrderRequest order, BindingResult bindingResult) {

        return ResponseEntity.ok(orderMapper.postOrder(order, bindingResult));
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewResponse> addReviewToProduct(@Valid @RequestBody ReviewRequest reviewRequest,
                                                             BindingResult bindingResult) {
        ReviewResponse review = userMapper.addReviewToProduct(reviewRequest, reviewRequest.getProductId(), bindingResult);
        messagingTemplate.convertAndSend("/topic/reviews/" + reviewRequest.getProductId(), review);
        return ResponseEntity.ok(review);
    }

    @PostMapping("/graphql/info")
    public ResponseEntity<ExecutionResult> getUserInfoByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/orders")
    public ResponseEntity<ExecutionResult> getUserOrdersByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }
}
