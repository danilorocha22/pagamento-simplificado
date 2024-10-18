package com.danilo.pagamentosimplificado.app.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LojistaOutput extends UsuarioOutput {
    private String cnpj;
}