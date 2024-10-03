package com.danilo.pagamentosimplificado.app.domain.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Comum;
import com.danilo.pagamentosimplificado.app.domain.entities.Lojista;
import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.exceptions.UsuarioExisteException;
import com.danilo.pagamentosimplificado.app.domain.repositories.ComumRepository;
import com.danilo.pagamentosimplificado.app.domain.repositories.LojistaRepository;
import com.danilo.pagamentosimplificado.app.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioValidacaoService {
    private final UsuarioRepository usuarioRepository;
    private final ComumRepository comumRepository;
    private final LojistaRepository lojistaRepository;

    public void validarUsuarioExiste(Usuario usuario) {
        this.validarEmailCadastrado(usuario);
        if (usuario instanceof Comum comum) {
            this.validarCpfCadastrado(comum);
        } else if (usuario instanceof Lojista lojista) {
            this.validarCnpjCadastrado(lojista);
        }
    }

    private void validarEmailCadastrado(Usuario usuario) {
        String email = usuario.getEmail();
        this.usuarioRepository.findByEmail(email)
                .ifPresent(usuarioExiste -> {
                    if (usuarioExiste.isDiferente(usuario)) {
                        throw new UsuarioExisteException("Já existe usuaŕio cadastrado com e-mail: %s".formatted(email));
                    }
                });
    }

    private void validarCpfCadastrado(Comum usuario) {
        String cpf = usuario.getCpf();
        this.comumRepository.findByCpf(cpf)
                .ifPresent(usuarioExiste -> {
                    if (usuarioExiste.isDiferente(usuario)) {
                        throw new UsuarioExisteException("Já existe usuário cadastrado com o CPF: %s".formatted(cpf));
                    }
                });
    }

    private void validarCnpjCadastrado(Lojista usuario) {
        String cnpj = usuario.getCnpj();
        this.lojistaRepository.findByCnpj(cnpj)
                .ifPresent(usuarioExiste -> {
                    if (usuarioExiste.isDiferente(usuario)) {
                        throw new UsuarioExisteException("Já existe lojistas cadastrados com o CNPJ: %s".formatted(cnpj));
                    }
                });
    }
}