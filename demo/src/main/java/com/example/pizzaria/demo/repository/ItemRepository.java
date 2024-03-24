package com.example.pizzaria.demo.repository;

import com.example.pizzaria.demo.entity.Item;
import com.example.pizzaria.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByOrder(Order order);
   // List<Item> findByAllOrders();
}
