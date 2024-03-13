package com.example.pizzaria.demo.web.dto.orderDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private String table;
    private String name;
    private String draft;
    private String status;
}
