package com.example.pizzaria.demo.web.dto.orderDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long id;
    /*private String table;
    private String name;
    private String draft;*/
    private String status;
}
