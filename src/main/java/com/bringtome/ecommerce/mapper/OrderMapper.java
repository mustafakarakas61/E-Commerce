package com.bringtome.ecommerce.mapper;

import com.bringtome.ecommerce.entity.OrderEntity;
import com.bringtome.ecommerce.dto.HeaderResponse;
import com.bringtome.ecommerce.dto.order.OrderItemResponse;
import com.bringtome.ecommerce.dto.order.OrderRequest;
import com.bringtome.ecommerce.dto.order.OrderResponse;
import com.bringtome.ecommerce.exception.InputFieldException;
import com.bringtome.ecommerce.service.OrderService;
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
public class OrderMapper {

    private final CommonMapper commonMapper;
    private final OrderService orderService;
    
    public OrderResponse getOrderById(Long orderId) {
        return commonMapper.convertToResponse(orderService.getOrderById(orderId), OrderResponse.class);
    }
    
    public List<OrderItemResponse> getOrderItemsByOrderId(Long orderId) {
        return commonMapper.convertToResponseList(orderService.getOrderItemsByOrderId(orderId), OrderItemResponse.class);
    }

    public HeaderResponse<OrderResponse> getAllOrders(PageRequest pageRequest) {
        Page<OrderEntity> orders = orderService.getAllOrders(pageRequest);
        return commonMapper.getHeaderResponse(orders.getContent(), orders.getTotalPages(), orders.getTotalElements(), OrderResponse.class);
    }

    public HeaderResponse<OrderResponse> getUserOrders(String email, PageRequest pageRequest) {
        Page<OrderEntity> orders = orderService.getUserOrders(email, pageRequest);
        return commonMapper.getHeaderResponse(orders.getContent(), orders.getTotalPages(), orders.getTotalElements(), OrderResponse.class);
    }

    public String deleteOrder(Long orderId) {
        return orderService.deleteOrder(orderId);
    }

    public OrderResponse postOrder(OrderRequest orderRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        OrderEntity order = orderService.postOrder(commonMapper.convertToEntity(orderRequest, OrderEntity.class), orderRequest.getProductsId());
        return commonMapper.convertToResponse(order, OrderResponse.class);
    }
}
