package com.danilo.pagamentosimplificado.app.domain.services.strategy.impl;

import com.danilo.pagamentosimplificado.app.domain.entities.Comum;
import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.exceptions.UsuarioExisteException;
import com.danilo.pagamentosimplificado.app.domain.repositories.ComumRepository;
import com.danilo.pagamentosimplificado.app.domain.services.strategy.UsuarioValidacaoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CpfValidacaoStrategy implements UsuarioValidacaoStrategy {
    private final ComumRepository comumRepository;

    @Override
    public void validarExiste(Usuario usuario) {
        if (usuario instanceof Comum comum) {
            String cpf = comum.getCpf();
            comumRepository.findByCpf(cpf)
                    .ifPresent(usuarioExiste -> {
                        if (usuarioExiste.isDiferente(usuario)) {
                            throw new UsuarioExisteException("Já existe usuário cadastrado com o CPF: %s".formatted(cpf));
                        }
                    });
        }
    }
}