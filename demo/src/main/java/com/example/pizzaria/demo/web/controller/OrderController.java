package com.example.pizzaria.demo.web.controller;

import com.example.pizzaria.demo.entity.Order;
import com.example.pizzaria.demo.service.OrderService;
import com.example.pizzaria.demo.web.dto.mapper.OrderMapper;
import com.example.pizzaria.demo.web.dto.orderDto.CreateOrderDto;
import com.example.pizzaria.demo.web.dto.orderDto.OrderDetailDto;
import com.example.pizzaria.demo.web.dto.orderDto.OrderDto;
import com.example.pizzaria.demo.web.dto.orderDto.OrderResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderResponseDto> deletarOrder(@RequestParam Long id) {
        orderService.deletar(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN' or 'CLIENTE')")
    public ResponseEntity<OrderResponseDto> detalheOrder(@PathVariable Long id) {
        Order order = orderService.buscarPorId(id);
        return ResponseEntity.ok().body(OrderMapper.toDto(order));
    }

    @PutMapping("/enviaOrder")
    @PreAuthorize("hasRole('ADMIN' or 'CLIENTE')")
    public ResponseEntity<OrderResponseDto> atualizarOrder(@RequestBody OrderDto id) {
        Order order = orderService.atualizaOrder(id.getId());
        return ResponseEntity.ok().body(OrderMapper.toDto(order));
    }

    @PutMapping("/finalizaOrder")
    @PreAuthorize("hasRole('ADMIN' or 'CLIENTE')")
    public ResponseEntity<OrderResponseDto> finalizaOrder(@RequestBody OrderDto id) {
        Order order = orderService.finalizaOrder(id.getId());

        return ResponseEntity.ok().body(OrderMapper.toDto(order));
    }

    //Criar o listar Orders e a paginação

    @GetMapping("/buscaOrderStatus")
    @PreAuthorize("hasRole('ADMIN' or 'CLIENTE')")
    public ResponseEntity<List<OrderResponseDto>> listaOrderStatus(@RequestBody OrderDto dto) {
        List<Order> order = orderService.listaOrderStatus(dto);

        return ResponseEntity.ok().body(OrderMapper.toListDto(order));
    }

    /*@GetMapping
    public ResponseEntity<List<OrderResponseDto>> listarOrders() {
        return ResponseEntity.ok().body(OrderMapper.toListDto(orderService.listarOrder()));
    } */

    @GetMapping("/order_detail/{id}")
    @PreAuthorize("hasRole('ADMIN' or 'CLIENTE')")
    public ResponseEntity<OrderDetailDto> orderDetail(@PathVariable Long id) {
        return ResponseEntity.ok().body(OrderMapper.toDetailDto(orderService.detalharOrder(id)));
    }

    @GetMapping("/order_detail")
    @PreAuthorize("hasRole('ADMIN' or 'CLIENTE')")
    public ResponseEntity<List<OrderDetailDto>> ListOrderDetail(){
        return ResponseEntity.ok().body(OrderMapper.toListOrderDetailDto(orderService.detalharAllOrders()));
    }

}
