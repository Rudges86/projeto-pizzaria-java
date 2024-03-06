package com.example.pizzaria.demo.exception;

public class EntityNotFoundException extends RuntimeException {
    private String recurso;
    private String codigo;
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String recurso, String codigo) {
        this.recurso = recurso;
        this.codigo = codigo;
    }
}
