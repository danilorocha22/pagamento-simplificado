package com.danilo.pagamentosimplificado.app.infra.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import com.danilo.pagamentosimplificado.app.domain.services.NotificacaoRecebedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificacaoImpl implements NotificacaoRecebedorService {
    private final NotificacaoExternalService notificacaoService;

    @Override
    public void transferenciaRealizada(Transferencia transferencia) {
        this.notificacaoService.notificar(transferencia);
    }
}