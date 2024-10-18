package com.danilo.pagamentosimplificado.app.infra.listeners;

import com.danilo.pagamentosimplificado.app.domain.events.TransferenciaRealizadaEvent;
import com.danilo.pagamentosimplificado.app.domain.listener.NotificacaoListener;
import com.danilo.pagamentosimplificado.app.domain.services.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoListenerImpl implements NotificacaoListener {

    @Autowired
    private NotificacaoService notificacaoService;

    @Override
    @TransactionalEventListener
    public void aoRealizarTransferencia(TransferenciaRealizadaEvent event) {
        this.notificacaoService.transferenciaRealizada(event.transferencia());
    }
}