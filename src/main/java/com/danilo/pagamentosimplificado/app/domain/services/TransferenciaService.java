package com.danilo.pagamentosimplificado.app.domain.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import com.danilo.pagamentosimplificado.app.domain.repositories.TransferenciaRepository;
import com.danilo.pagamentosimplificado.app.infra.services.TransacaoAutorizadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferenciaService {
    private final TransferenciaRepository transferenciaRepository;
    private final TransacaoAutorizadorService autorizadorService;

    @Transactional
    public void realizarTransferencia(Transferencia transferencia) {
        this.validar(transferencia);
        this.autorizadorService.autorizarTransacao();
        transferencia.getPagador().debitarSaldo(transferencia.getValor());
        transferencia.getRecebedor().adicionarSaldo(transferencia.getValor());
        this.transferenciaRepository.save(transferencia);
    }

    private void validar(Transferencia transferencia) {
        transferencia.validarValorTransferido();
        transferencia.impedirLogistaRealizar();
    }
}