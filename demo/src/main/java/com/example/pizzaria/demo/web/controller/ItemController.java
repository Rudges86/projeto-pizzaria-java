package com.example.pizzaria.demo.web.controller;

import com.example.pizzaria.demo.entity.Item;
import com.example.pizzaria.demo.service.ItemService;
import com.example.pizzaria.demo.web.dto.itemDto.CreateItemDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/addItem")
    public ResponseEntity<Item> salvarItem(@RequestBody CreateItemDto item) {
        Item itemSalvo = itemService.salvarItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemSalvo);
    }

    @DeleteMapping("/deleteItem")
    public ResponseEntity deletarItem(@RequestParam Long id) {
        itemService.deletarItem(id);
        return ResponseEntity.ok().build();
    }
}
