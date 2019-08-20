package com.example.demo.feature.order.repository;

import com.example.demo.feature.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepostory extends JpaRepository<OrderEntity, Long> {
}
