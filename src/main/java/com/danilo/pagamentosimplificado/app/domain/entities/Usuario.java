package com.danilo.pagamentosimplificado.app.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = SINGLE_TABLE)
public abstract class Usuario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String senha;
}