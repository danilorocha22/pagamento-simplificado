package com.danilo.pagamentosimplificado.app.domain.exceptions;

import jakarta.persistence.PersistenceException;

public class EntidadeNaoCadastradaException extends PersistenceException {

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