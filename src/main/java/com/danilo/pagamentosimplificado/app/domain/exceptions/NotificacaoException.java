package com.danilo.pagamentosimplificado.app.domain.exceptions;

public class NotificacaoException extends RuntimeException {

    public NotificacaoException() {
        super("Falhou ao enviar a notificação");
    }

    public NotificacaoException(String message) {
        this(message, null);
    }

    public NotificacaoException(String message, Throwable cause) {
        super(message, cause);
    }
}