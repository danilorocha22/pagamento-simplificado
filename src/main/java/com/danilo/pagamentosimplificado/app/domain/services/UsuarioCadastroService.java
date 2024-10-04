package com.danilo.pagamentosimplificado.app.domain.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.exceptions.EntidadeNaoCadastradaException;
import com.danilo.pagamentosimplificado.app.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioCadastroService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioValidacaoService usuarioValidacaoService;

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario) {
        this.usuarioValidacaoService.validarUsuarioExiste(usuario);
        return Optional.of(this.usuarioRepository.save(usuario))
                .orElseThrow(() -> new EntidadeNaoCadastradaException("Falhou ao tentar cadastrar o usuário: %s"
                        .formatted(usuario.getNomeCompleto())));
    }

    public Usuario buscarPorId(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoCadastradaException("Usuario não encontrado"));
    }
}