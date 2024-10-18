package com.danilo.pagamentosimplificado.app.infra.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import com.danilo.pagamentosimplificado.app.domain.services.NotificacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificacaoServiceImpl implements NotificacaoService {
    private final NotificacaoExternaService notificacaoService;

    @Async
    @Override
    public void transferenciaRealizada(Transferencia transferencia) {
        log.info("Iniciando serviço de notificação.");
        this.notificacaoService.notificar(transferencia);
    }
}