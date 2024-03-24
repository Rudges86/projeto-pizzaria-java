package com.example.pizzaria.demo.web.dto.orderDto;

import com.example.pizzaria.demo.entity.Order;
import com.example.pizzaria.demo.entity.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
    private Long id;
    private int amount;
    private Order order;
    private Produto produto;
}
