package com.danilo.pagamentosimplificado.app.domain.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;

public interface NotificacaoRecebedorService {
    void transferenciaRealizada(Transferencia transferencia);
}
