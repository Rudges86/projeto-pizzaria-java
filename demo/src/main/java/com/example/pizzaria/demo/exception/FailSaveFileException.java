package com.example.pizzaria.demo.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
@Getter
@Setter
public class FailSaveFileException extends RuntimeException {
    private String message;

    public FailSaveFileException() {
    }

    public FailSaveFileException(String message) {
        super(message);
        this.message = message;
    }

    public FailSaveFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailSaveFileException(Throwable cause) {
        super(cause);
    }

    public FailSaveFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
