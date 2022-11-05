package com.bmod.ecommerce.service;

import com.bmod.ecommerce.entity.OrderEntity;
import com.bmod.ecommerce.entity.OrderItemEntity;
import graphql.schema.DataFetcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderEntity getOrderById(Long orderId);

    List<OrderItemEntity> getOrderItemsByOrderId(Long orderId);
    
    Page<OrderEntity> getAllOrders(Pageable pageable);

    Page<OrderEntity> getUserOrders(String email, Pageable pageable);

    OrderEntity postOrder(OrderEntity validOrder, Map<Long, Long> perfumesId);

    String deleteOrder(Long orderId);

    DataFetcher<List<OrderEntity>> getAllOrdersByQuery();

    DataFetcher<List<OrderEntity>> getUserOrdersByEmailQuery();
}
