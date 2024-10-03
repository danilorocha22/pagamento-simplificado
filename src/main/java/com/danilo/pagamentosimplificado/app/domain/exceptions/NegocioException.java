package com.danilo.pagamentosimplificado.app.domain.exceptions;

public class NegocioException extends RuntimeException {

    public NegocioException() {
        super();
    }

    public NegocioException(String message) {
        this(message, null);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}