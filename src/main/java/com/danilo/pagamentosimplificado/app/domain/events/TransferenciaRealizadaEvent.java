package com.danilo.pagamentosimplificado.app.domain.events;

import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;

public record TransferenciaRealizadaEvent(Transferencia transferencia) {
}
