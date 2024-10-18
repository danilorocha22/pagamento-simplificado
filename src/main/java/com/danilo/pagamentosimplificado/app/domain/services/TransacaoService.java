package com.danilo.pagamentosimplificado.app.domain.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Carteira;
import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.repositories.TransacaoRepository;
import com.danilo.pagamentosimplificado.app.infra.services.AutorizadorExternoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final AutorizadorExternoService autorizadorExternoService;
    private final UsuarioService usuarioService;

    @Transactional
    public Carteira depositar(Long usuarioId, BigDecimal valor) {
        Usuario usuario = this.usuarioService.buscarPorId(usuarioId);
        usuario.getCarteira().creditar(valor);
        return usuario.getCarteira();
    }

    @Transactional
    public Transferencia transferir(Transferencia transferencia) {
        this.validar(transferencia);
        transferencia.getPagador().debitar(transferencia.getValor());
        transferencia.getRecebedor().creditar(transferencia.getValor());
        return this.transacaoRepository.saveAndFlush(transferencia);
    }

    private void validar(Transferencia transferencia) {
        transferencia.getPagador().validarValorTransacao(transferencia.getValor());
        transferencia.impedirLogistaTransferir();
        transferencia.impedirPagadorIgualRecebedor();
        this.autorizadorExternoService.autorizarTransacao();
    }
}