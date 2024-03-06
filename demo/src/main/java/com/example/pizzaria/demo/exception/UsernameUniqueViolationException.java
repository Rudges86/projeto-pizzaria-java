package com.example.pizzaria.demo.exception;

import lombok.Getter;

@Getter
public class UsernameUniqueViolationException extends RuntimeException{
    private String name;

    public UsernameUniqueViolationException(String name) {
        this.name = name;
    }
}
