package com.danilo.pagamentosimplificado.app.domain.exceptions;

public class TransferenciaException extends NegocioException {

    public TransferenciaException() {
        this(null);
    }

    public TransferenciaException(String message) {
        this(message, null);
    }

    public TransferenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
