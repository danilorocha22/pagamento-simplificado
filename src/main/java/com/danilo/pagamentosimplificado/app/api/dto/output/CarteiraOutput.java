package com.danilo.pagamentosimplificado.app.api.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarteiraOutput {
    private Long id;
    private String saldo;
    @JsonProperty("usuário")
    private String usuarioNomeCompleto;
}