package com.danilo.pagamentosimplificado.app.domain.entities;

import com.danilo.pagamentosimplificado.app.domain.exceptions.TransferenciaException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.math.BigDecimal.ZERO;

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
    private BigDecimal saldo = ZERO;

    @NotNull
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void adicionarSaldo(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void debitarSaldo(BigDecimal valor) {
        this.validarSaldo(valor);
        this.saldo = this.saldo.subtract(valor);
    }

    public void validarSaldo(BigDecimal valor) {
        if (isInsuficiente(valor)) {
            throw new TransferenciaException("Saldo insuficiente");
        }
    }

    private boolean isInsuficiente(BigDecimal valor) {
        return this.saldo.compareTo(ZERO) == 0 || this.saldo.subtract(valor).compareTo(ZERO) < 0;
    }
}