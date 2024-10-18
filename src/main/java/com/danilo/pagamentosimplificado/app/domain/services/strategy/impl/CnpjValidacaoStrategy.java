package com.danilo.pagamentosimplificado.app.domain.services.strategy.impl;

import com.danilo.pagamentosimplificado.app.domain.entities.Lojista;
import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.exceptions.UsuarioExisteException;
import com.danilo.pagamentosimplificado.app.domain.repositories.LojistaRepository;
import com.danilo.pagamentosimplificado.app.domain.services.strategy.UsuarioValidacaoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CnpjValidacaoStrategy implements UsuarioValidacaoStrategy {
    private final LojistaRepository lojistaRepository;

    @Override
    public void validarExiste(Usuario usuario) {
        if (usuario instanceof Lojista lojista) {
            String cnpj = lojista.getCnpj();
            lojistaRepository.findByCnpj(cnpj)
                    .ifPresent(usuarioExiste -> {
                        if (usuarioExiste.isDiferente(usuario)) {
                            throw new UsuarioExisteException("Já existe usuário cadastrado com o CNPJ: %s".formatted(cnpj));
                        }
                    });
        }
    }
}