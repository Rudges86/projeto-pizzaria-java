package com.example.pizzaria.demo.web.dto.mapper;

import com.example.pizzaria.demo.entity.Item;
import com.example.pizzaria.demo.entity.Order;
import com.example.pizzaria.demo.web.dto.orderDto.CreateOrderDto;
import com.example.pizzaria.demo.web.dto.orderDto.OrderDetailDto;
import com.example.pizzaria.demo.web.dto.orderDto.OrderDto;
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

    public static OrderDetailDto toDetailDto(Item item) {return new ModelMapper().map(item, OrderDetailDto.class);}

    public static List<OrderResponseDto> toListDto(List<Order> orders) {
        return orders.stream().map(order -> toDto(order)).collect(Collectors.toList());
    }

    public static List<OrderDetailDto> toListOrderDetailDto(List<Item> itens) {
        return itens.stream().map(item -> toDetailDto(item)).collect(Collectors.toList());
    }


}
