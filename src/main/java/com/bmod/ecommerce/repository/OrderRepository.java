package com.bmod.ecommerce.repository;

import com.bmod.ecommerce.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByOrderByIdAsc();

    Page<OrderEntity> findAllByOrderByIdAsc(Pageable pageable);

    List<OrderEntity> findOrderByEmail(String email);

    Page<OrderEntity> findOrderByEmail(String email, Pageable pageable);
}
