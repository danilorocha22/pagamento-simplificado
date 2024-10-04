package com.danilo.pagamentosimplificado.app.api.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferenciaOutput {
    @JsonProperty("código da transação")
    private String codigo;
    @JsonProperty("pagador")
    private String pagadorUsuarioNomeCompleto;
    @JsonProperty("recebedor")
    private String recebedorUsuarioNomeCompleto;
    private BigDecimal valor;
}