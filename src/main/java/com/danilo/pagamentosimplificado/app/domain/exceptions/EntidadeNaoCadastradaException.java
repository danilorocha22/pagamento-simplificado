package com.danilo.pagamentosimplificado.app.domain.exceptions;

public class EntidadeNaoCadastradaException extends RuntimeException {

    public EntidadeNaoCadastradaException() {
        super();
    }

    public EntidadeNaoCadastradaException(String message) {
        this(message, null);
    }

    public EntidadeNaoCadastradaException(String message, Throwable cause) {
        super(message, cause);
    }
}