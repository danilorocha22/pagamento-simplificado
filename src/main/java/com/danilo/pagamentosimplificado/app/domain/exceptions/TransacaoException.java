package com.danilo.pagamentosimplificado.app.domain.exceptions;

public class TransacaoException extends NegocioException {

    public TransacaoException() {
        this(null);
    }

    public TransacaoException(String message) {
        this(message, null);
    }

    public TransacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
