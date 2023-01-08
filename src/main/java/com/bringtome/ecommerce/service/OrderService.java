package com.bringtome.ecommerce.service;

import com.bringtome.ecommerce.entity.OrderEntity;
import com.bringtome.ecommerce.entity.OrderItemEntity;
import graphql.schema.DataFetcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:3000")
public interface OrderService {

    OrderEntity getOrderById(Long orderId);

    List<OrderItemEntity> getOrderItemsByOrderId(Long orderId);
    
    Page<OrderEntity> getAllOrders(PageRequest pageRequest);

    Page<OrderEntity> getUserOrders(String email, PageRequest pageRequest);

    OrderEntity postOrder(OrderEntity validOrder, Map<Long, Long> productsId);

    String deleteOrder(Long orderId);

    DataFetcher<List<OrderEntity>> getAllOrdersByQuery();

    DataFetcher<List<OrderEntity>> getUserOrdersByEmailQuery();
}
