package com.danilo.pagamentosimplificado.app.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@Entity
@DiscriminatorValue("lojista")
@EqualsAndHashCode(callSuper = true)
public class Lojista extends Usuario {

    @NotBlank
    @Column(unique = true)
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;
}