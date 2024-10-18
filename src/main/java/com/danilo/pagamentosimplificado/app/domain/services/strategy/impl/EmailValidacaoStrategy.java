package com.danilo.pagamentosimplificado.app.domain.services.strategy.impl;

import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.exceptions.UsuarioExisteException;
import com.danilo.pagamentosimplificado.app.domain.repositories.UsuarioRepository;
import com.danilo.pagamentosimplificado.app.domain.services.strategy.UsuarioValidacaoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailValidacaoStrategy implements UsuarioValidacaoStrategy {
    private final UsuarioRepository usuarioRepository;

    @Override
    public void validarExiste(Usuario usuario) {
        String email = usuario.getEmail();
        usuarioRepository.findByEmail(email)
                .ifPresent(usuarioExiste -> {
                    if (usuarioExiste.isDiferente(usuario)) {
                        throw new UsuarioExisteException("Já existe usuário cadastrado com e-mail: %s".formatted(email));
                    }
                });
    }
}