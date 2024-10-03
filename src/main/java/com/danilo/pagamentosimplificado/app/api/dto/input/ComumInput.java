package com.danilo.pagamentosimplificado.app.api.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class ComumInput {
    @NotBlank
    private String nomeCompleto;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @CPF
    @NotBlank
    private String cpf;
}