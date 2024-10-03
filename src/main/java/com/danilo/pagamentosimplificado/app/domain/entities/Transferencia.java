package com.danilo.pagamentosimplificado.app.domain.entities;

import com.danilo.pagamentosimplificado.app.domain.exceptions.TransferenciaException;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import static jakarta.persistence.CascadeType.REFRESH;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.math.BigDecimal.ZERO;
import static java.util.UUID.randomUUID;

@Getter
@Setter
@Entity
@Table(name = "transacoes")
@EqualsAndHashCode(of = "id")
public class Transferencia implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String codigo;

    @NotNull
    @DecimalMin(value = "0.01", message = "O valor da transação deve ser maior que 0")
    private BigDecimal valor;

    @NotNull
    @JoinColumn(name = "pagador_id")
    @ManyToOne(cascade = REFRESH, fetch = LAZY)
    private Carteira pagador;

    @NotNull
    @JoinColumn(name = "recebedor_id")
    @ManyToOne(cascade = REFRESH, fetch = LAZY)
    private Carteira recebedor;

    @PrePersist
    private void gerarCodigoTransacao() {
        this.setCodigo(randomUUID().toString());
    }

    public void validarValorTransferido() {
        if (this.getValor().compareTo(ZERO) <= 0) {
            throw new TransferenciaException("O valor da transação deve ser maior que 0");
        }
    }

    public void impedirLogistaRealizar() {
        if (this.getPagador().getUsuario().isLogista()) {
            throw new TransferenciaException("Apenas usuários comuns podem realizar uma transferência");
        }
    }
}