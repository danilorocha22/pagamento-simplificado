package com.danilo.pagamentosimplificado.app.api.dto.input;

import com.danilo.pagamentosimplificado.app.domain.entities.Carteira;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferenciaInput {
    @NotNull
    private Carteira pagador;

    @NotNull
    private Carteira recebedor;

    @NotNull
    private BigDecimal valor;
}