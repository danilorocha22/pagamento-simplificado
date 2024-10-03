package com.danilo.pagamentosimplificado.app.api.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComumOutput {
    private Long id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String cpf;
}