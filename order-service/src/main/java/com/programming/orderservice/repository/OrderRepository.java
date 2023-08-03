package com.programming.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programming.orderservice.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
}

