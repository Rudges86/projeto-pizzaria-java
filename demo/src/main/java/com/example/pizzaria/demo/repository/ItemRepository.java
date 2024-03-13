package com.example.pizzaria.demo.repository;

import com.example.pizzaria.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
