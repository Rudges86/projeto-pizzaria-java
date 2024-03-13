package com.example.pizzaria.demo.service;

import com.example.pizzaria.demo.entity.Order;
import com.example.pizzaria.demo.exception.EntityNotFoundException;
import com.example.pizzaria.demo.repository.OrderRepository;
import com.example.pizzaria.demo.web.dto.orderDto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public Order buscarPorId(Long id) {
        return orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Order não encontrada"));
    }

    @Transactional
    public Order atualizaOrder(Long id) {
        Order order = buscarPorId(id);
        order.setDraft(false);
        atualizaStatus(order, "produção");
        return order;
    }

    @Transactional
    public Order finalizaOrder(Long id) {
        Order order = buscarPorId(id);
        atualizaStatus(order, "finalizado");

        return order;
    }

    @Transactional(readOnly = true)
    public List<Order> listaOrderStatus(OrderDto dto) {
        List<Order> listaOrder = orderRepository.findByStatus(Order.Status.valueOf(dto.getStatus().toUpperCase()));
        return listaOrder;

    }

    private void atualizaStatus (Order order, String tipo) {

        if(order.getStatus() == Order.Status.AGUARDANDO && tipo.equals("produção")) {
            order.setStatus(Order.Status.EM_PRODUCAO);
        }

        if(order.getStatus() == Order.Status.EM_PRODUCAO && tipo.equals("finalizado")) {
            order.setStatus(Order.Status.FINALIZADO);
        }

    }

}
