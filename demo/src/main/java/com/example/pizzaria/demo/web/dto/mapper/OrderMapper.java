package com.example.pizzaria.demo.web.dto.mapper;

import com.example.pizzaria.demo.entity.Order;
import com.example.pizzaria.demo.web.dto.orderDto.CreateOrderDto;
import com.example.pizzaria.demo.web.dto.orderDto.OrderResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static Order toOrder(CreateOrderDto createDto) {
        return new ModelMapper().map(createDto, Order.class);
    }
    public static Order toOrder(OrderResponseDto createDto) {
        return new ModelMapper().map(createDto, Order.class);
    }
    public static OrderResponseDto toDto(Order order) {
        return new ModelMapper().map(order, OrderResponseDto.class);
    }

    public static List<OrderResponseDto> toListDto(List<Order> orders) {
        return orders.stream().map(order -> toDto(order)).collect(Collectors.toList());
    }
}
