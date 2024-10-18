package com.danilo.pagamentosimplificado.app.api.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarteiraSaldoOutput {
    private String saldo;
}