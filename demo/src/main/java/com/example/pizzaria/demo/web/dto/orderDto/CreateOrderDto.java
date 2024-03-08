package com.example.pizzaria.demo.web.dto.orderDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderDto {
    private String table;
    private String name;
}
