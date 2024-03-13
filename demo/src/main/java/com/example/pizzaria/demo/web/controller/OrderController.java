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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public ResponseEntity<OrderResponseDto> criarOrder(@RequestBody @Valid CreateOrderDto orderDto) {
        Order order = orderService.salvar(OrderMapper.toOrder(orderDto));
        return ResponseEntity.ok().body(OrderMapper.toDto(order));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE)")
    public ResponseEntity<OrderResponseDto> deletarOrder(@RequestParam Long id) {
        orderService.deletar(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> detalheOrder(@PathVariable Long id) {
        Order order = orderService.buscarPorId(id);
        return ResponseEntity.ok().body(OrderMapper.toDto(order));
    }

    @PutMapping("/enviaOrder")
    public ResponseEntity<OrderResponseDto> atualizarOrder(@RequestBody OrderDto id) {
        Order order = orderService.atualizaOrder(id.getId());
        return ResponseEntity.ok().body(OrderMapper.toDto(order));
    }

    @PutMapping("/finalizaOrder")
    public ResponseEntity<OrderResponseDto> finalizaOrder(@RequestBody OrderDto id) {
        Order order = orderService.finalizaOrder(id.getId());

        return ResponseEntity.ok().body(OrderMapper.toDto(order));
    }

    //Criar o listar Orders e a paginação

    @GetMapping("/buscaOrderStatus")
    public ResponseEntity<List<OrderResponseDto>> listaOrderStatus(@RequestBody OrderDto dto) {
        List<Order> order = orderService.listaOrderStatus(dto);

        return ResponseEntity.ok().body(OrderMapper.toListDto(order));
    }

}
