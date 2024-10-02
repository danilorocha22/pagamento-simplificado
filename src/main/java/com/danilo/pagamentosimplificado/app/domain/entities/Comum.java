package com.danilo.pagamentosimplificado.app.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("comum")
@EqualsAndHashCode(callSuper = true)
public class Comum extends Usuario {

    @NotBlank
    @Column(unique = true)
    private String cpf;
}