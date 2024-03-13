package com.example.pizzaria.demo.service;

import com.example.pizzaria.demo.entity.Item;
import com.example.pizzaria.demo.entity.Order;
import com.example.pizzaria.demo.entity.Produto;
import com.example.pizzaria.demo.exception.InvalidSaveItem;
import com.example.pizzaria.demo.repository.ItemRepository;
import com.example.pizzaria.demo.web.dto.itemDto.CreateItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final OrderService orderService;
    private final ProdutoService produtoService;

    @Transactional
    public Item salvarItem(CreateItemDto createItemDto){
        try {
            Produto produto = produtoService.buscarPorId(createItemDto.getProdutoId());
            Order order = orderService.buscarPorId(createItemDto.getOrderId());

            Item item = new Item();
            item.setAmount(createItemDto.getAmount());
            item.setOrder(order);
            item.setProduto(produto);

          return  itemRepository.save(item);
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidSaveItem(ex.getMessage());
        }
    }


}
