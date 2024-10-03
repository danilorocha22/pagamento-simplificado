package com.danilo.pagamentosimplificado.app.domain.exceptions;

public class UsuarioExisteException extends NegocioException {

    public UsuarioExisteException() {
        this("O usuário já existe");
    }

    public UsuarioExisteException(String message) {
        this(message, null);
    }

    public UsuarioExisteException(String message, Throwable cause) {
        super(message, cause);
    }
}
