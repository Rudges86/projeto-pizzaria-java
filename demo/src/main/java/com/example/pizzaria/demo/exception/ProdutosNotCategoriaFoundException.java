package com.example.pizzaria.demo.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutosNotCategoriaFoundException extends RuntimeException{
    private String message;
    private String codigo;


    public ProdutosNotCategoriaFoundException(String message, String codigo) {
        this.message = message;
        this.codigo = codigo;
    }

    public ProdutosNotCategoriaFoundException(String message) {
        super(message);
        this.message = message;
    }


}
