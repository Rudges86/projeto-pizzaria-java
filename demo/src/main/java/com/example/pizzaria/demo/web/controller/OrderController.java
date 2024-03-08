package com.example.pizzaria.demo.web.controller;

import com.example.pizzaria.demo.entity.Order;
import com.example.pizzaria.demo.service.OrderService;
import com.example.pizzaria.demo.web.dto.mapper.OrderMapper;
import com.example.pizzaria.demo.web.dto.orderDto.CreateOrderDto;
import com.example.pizzaria.demo.web.dto.orderDto.OrderDto;
import com.example.pizzaria.demo.web.dto.orderDto.OrderResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> criarOrder(@RequestBody @Valid CreateOrderDto orderDto) {
        Order order = orderService.salvar(OrderMapper.toOrder(orderDto));
        return ResponseEntity.ok().body(OrderMapper.toDto(order));
    }

    @DeleteMapping
    public ResponseEntity<OrderResponseDto> deletarOrder(@RequestParam Long id) {
        orderService.deletar(id);
        return ResponseEntity.ok().build();

    }
}
