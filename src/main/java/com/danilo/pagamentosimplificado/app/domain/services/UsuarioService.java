package com.danilo.pagamentosimplificado.app.domain.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.exceptions.EntidadeNaoCadastradaException;
import com.danilo.pagamentosimplificado.app.domain.repositories.UsuarioRepository;
import com.danilo.pagamentosimplificado.app.domain.services.strategy.UsuarioValidacaoStrategy;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final List<UsuarioValidacaoStrategy> usuarioValidacaoStrategies;

    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        this.usuarioValidacaoStrategies.forEach(strategy -> strategy.validarExiste(usuario));
        return Optional.of(this.usuarioRepository.save(usuario))
                .orElseThrow(() -> new EntidadeNaoCadastradaException("Falhou ao tentar cadastrar o usuário: %s"
                        .formatted(usuario.getNomeCompleto())));
    }

    public Usuario buscarPorId(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
    }
}