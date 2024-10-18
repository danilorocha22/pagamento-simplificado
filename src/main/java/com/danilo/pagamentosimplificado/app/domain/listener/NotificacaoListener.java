package com.danilo.pagamentosimplificado.app.domain.listener;

import com.danilo.pagamentosimplificado.app.domain.events.TransferenciaRealizadaEvent;

public interface NotificacaoListener {
    void aoRealizarTransferencia(TransferenciaRealizadaEvent event);
}