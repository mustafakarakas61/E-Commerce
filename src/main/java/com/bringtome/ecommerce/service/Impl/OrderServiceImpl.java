package com.bringtome.ecommerce.service.Impl;

import com.bringtome.ecommerce.config.EmailConfig;
import com.bringtome.ecommerce.entity.OrderEntity;
import com.bringtome.ecommerce.entity.OrderItemEntity;
import com.bringtome.ecommerce.entity.ProductEntity;
import com.bringtome.ecommerce.exception.ApiRequestException;
import com.bringtome.ecommerce.repository.OrderItemRepository;
import com.bringtome.ecommerce.repository.OrderRepository;
import com.bringtome.ecommerce.repository.ProductRepository;
import com.bringtome.ecommerce.service.OrderService;
import com.bringtome.ecommerce.service.email.MailSender;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final MailSender mailSender;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository, MailSender mailSender) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.mailSender = mailSender;
    }

    @Override
    public OrderEntity getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ApiRequestException("Sipariş Bulunamadı.", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<OrderItemEntity> getOrderItemsByOrderId(Long orderId) {
        OrderEntity order = getOrderById(orderId);
        return order.getOrderItems();
    }

    @Override
    public Page<OrderEntity> getAllOrders(Pageable pageable) {
        return orderRepository.findAllByOrderByIdAsc(pageable);
    }

    @Override
    public Page<OrderEntity> getUserOrders(String email, Pageable pageable) {
        return orderRepository.findOrderByEmail(email, pageable);
    }

    @Override
    @Transactional
    public OrderEntity postOrder(OrderEntity validOrder, Map<Long, Long> productsId) {
        OrderEntity order = new OrderEntity();
        List<OrderItemEntity> orderItemList = new ArrayList<>();

        for (Map.Entry<Long, Long> entry : productsId.entrySet()) {
            ProductEntity product = productRepository.findById(entry.getKey()).get();
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setProduct(product);
            orderItem.setAmount((product.getPrice() * entry.getValue()));
            orderItem.setQuantity(entry.getValue());
            orderItemList.add(orderItem);
            orderItemRepository.save(orderItem);
        }
        order.getOrderItems().addAll(orderItemList);
        order.setTotalPrice(validOrder.getTotalPrice());
        order.setFirstName(validOrder.getFirstName());
        order.setLastName(validOrder.getLastName());
        order.setCity(validOrder.getCity());
        order.setAddress(validOrder.getAddress());
        order.setPostIndex(validOrder.getPostIndex());
        order.setEmail(validOrder.getEmail());
        order.setPhoneNumber(validOrder.getPhoneNumber());
        orderRepository.save(order);

        String subject = "Sipariş #" + order.getId();
        String template = "order-template";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("order", order);
        mailSender.sendMessageHtml(order.getEmail(), subject, template, attributes);
        return order;
    }

    @Override
    @Transactional
    public String deleteOrder(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApiRequestException("Sipariş bulunamadı.", HttpStatus.NOT_FOUND));
        orderRepository.delete(order);
        return "Sipariş başarıyla silindi!";
    }

    @Override
    public DataFetcher<List<OrderEntity>> getAllOrdersByQuery() {
        return dataFetchingEnvironment -> orderRepository.findAllByOrderByIdAsc();
    }

    @Override
    public DataFetcher<List<OrderEntity>> getUserOrdersByEmailQuery() {
        return dataFetchingEnvironment -> {
            String email = dataFetchingEnvironment.getArgument("email").toString();
            return orderRepository.findOrderByEmail(email);
        };
    }
}
