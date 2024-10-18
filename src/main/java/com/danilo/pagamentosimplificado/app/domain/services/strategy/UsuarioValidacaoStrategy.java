package com.danilo.pagamentosimplificado.app.domain.services.strategy;

import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;

public interface UsuarioValidacaoStrategy {

    void validarExiste(Usuario usuario);
}