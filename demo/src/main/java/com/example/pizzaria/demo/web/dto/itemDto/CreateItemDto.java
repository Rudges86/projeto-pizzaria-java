package com.example.pizzaria.demo.web.dto.itemDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemDto {
    private Long orderId;
    private Long produtoId;
    private Integer amount;

}
