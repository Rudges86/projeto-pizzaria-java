package com.example.pizzaria.demo.repository;

import com.example.pizzaria.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
