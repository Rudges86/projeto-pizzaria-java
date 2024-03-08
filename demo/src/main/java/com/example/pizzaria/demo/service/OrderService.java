package com.example.pizzaria.demo.service;

import com.example.pizzaria.demo.entity.Order;
import com.example.pizzaria.demo.repository.OrderRepository;
import com.example.pizzaria.demo.web.dto.orderDto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Order salvar(Order toOrder) {
        try {
            return orderRepository.save(toOrder);
        } catch (DataIntegrityViolationException ex){
            System.out.println(ex.getMessage());
        }
        return toOrder;
    }

    @Transactional
    public void deletar(Long id) {
         orderRepository.deleteById(id);
    }
}
