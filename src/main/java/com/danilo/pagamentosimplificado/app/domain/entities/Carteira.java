package com.danilo.pagamentosimplificado.app.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "carteiras")
@EqualsAndHashCode(of = "id")
public class Carteira implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal saldo = BigDecimal.ZERO;

    @NotNull
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}