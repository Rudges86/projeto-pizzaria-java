package com.example.pizzaria.demo.exception;

import lombok.Getter;

@Getter
public class InvalidCredencialException extends RuntimeException {
    private String nome;

    public InvalidCredencialException(String nome) {
        this.nome = nome;
    }
}
