package com.danilo.pagamentosimplificado.app.domain.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import com.danilo.pagamentosimplificado.app.domain.repositories.TransferenciaRepository;
import com.danilo.pagamentosimplificado.app.infra.services.AutorizadorExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransacoesService {
    private final TransacoesRepository transacoesRepository;
    private final AutorizadorExternalService autorizadorService;
    private final UsuarioCadastroService usuarioCadastroService;

    @Transactional
    public Carteira depositar(Long usuarioId, BigDecimal valor) {
        Usuario usuario = this.usuarioCadastroService.buscarPorId(usuarioId);
        usuario.getCarteira().adicionarSaldo(valor);
        return usuario.getCarteira();
    }

    @Transactional
    public Transferencia transferir(Transferencia transferencia) {
        this.validar(transferencia);
        this.autorizadorService.autorizarTransacao();
        transferencia.getPagador().debitarSaldo(transferencia.getValor());
        transferencia.getRecebedor().adicionarSaldo(transferencia.getValor());
        return this.transacoesRepository.saveAndFlush(transferencia);
    }

    private void validar(Transferencia transferencia) {
        transferencia.getPagador().validarValorTransacao(transferencia.getValor());
        transferencia.getRecebedor().getUsuario().impedirLogistaFazerTransferencia();
    }
}