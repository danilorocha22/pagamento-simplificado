package com.danilo.pagamentosimplificado.app.domain.exceptions;

public class TransacaoNaoAutorizadaException extends NegocioException {

    public TransacaoNaoAutorizadaException() {
        this("Esta transação não pode ser autorizada");
    }

    public TransacaoNaoAutorizadaException(String message) {
        this(message, null);
    }

    public TransacaoNaoAutorizadaException(String message, Throwable cause) {
        super(message, cause);
    }
}
