package com.danilo.pagamentosimplificado.app.infra.listeners;

import com.danilo.pagamentosimplificado.app.domain.events.TransferenciaRealizadaEvent;
import com.danilo.pagamentosimplificado.app.domain.listener.NotificacaoRecebedorListener;
import com.danilo.pagamentosimplificado.app.domain.services.NotificacaoRecebedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoRecebedorListenerImpl implements NotificacaoRecebedorListener {

    @Autowired
    private NotificacaoRecebedorService notificacaoRecebedorService;

    @Override
    @TransactionalEventListener
    public void aoRealizarTransferencia(TransferenciaRealizadaEvent event) {
        this.notificacaoRecebedorService.transferenciaRealizada(event.transferencia());
    }
}
