package com.danilo.pagamentosimplificado.app.domain.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;

public interface NotificacaoService {
    void transferenciaRealizada(Transferencia transferencia);
}