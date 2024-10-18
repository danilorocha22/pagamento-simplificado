package com.danilo.pagamentosimplificado.app.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TransferenciaInput {
    @NotNull
    private Long pagador;

    @NotNull
    private Long recebedor;

    @NotNull
    private BigDecimal valor;
}