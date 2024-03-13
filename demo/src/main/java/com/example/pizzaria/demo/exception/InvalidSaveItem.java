package com.example.pizzaria.demo.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InvalidSaveItem extends RuntimeException{
    private String message;
    private String codigo;

    public InvalidSaveItem(String message) {
        super(message);
        this.message = message;
    }

}
