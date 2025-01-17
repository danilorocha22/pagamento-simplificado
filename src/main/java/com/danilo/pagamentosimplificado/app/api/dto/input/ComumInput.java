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

    @NotBlank
    @Email(message = "Email inválido")
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    @CPF(message = "CPF inválido")
    private String cpf;
}