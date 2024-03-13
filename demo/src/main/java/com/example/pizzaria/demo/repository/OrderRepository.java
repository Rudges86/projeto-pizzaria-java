package com.example.pizzaria.demo.repository;

import com.example.pizzaria.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(Enum status);
}
