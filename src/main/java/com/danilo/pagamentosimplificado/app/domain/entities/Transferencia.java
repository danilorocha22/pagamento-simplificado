package com.danilo.pagamentosimplificado.app.domain.entities;

import com.danilo.pagamentosimplificado.app.domain.events.TransferenciaRealizadaEvent;
import com.danilo.pagamentosimplificado.app.domain.exceptions.TransacaoException;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import static jakarta.persistence.CascadeType.REFRESH;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.util.UUID.randomUUID;

@Getter
@Setter
@Entity
@ToString
@Table(name = "transacoes")
@EqualsAndHashCode(of = "id", callSuper = false)
public class Transferencia extends AbstractAggregateRoot<Transferencia> implements Serializable {
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

    @PostPersist
    private void NotificarRecebedor() {
        this.registerEvent(new TransferenciaRealizadaEvent(this));
    }

    public void impedirLogistaTransferir() {
        if (this.getPagador().getUsuario().isLogista()) {
            throw new TransacaoException("Logista não pode realizar transferências.");
        }
    }

    public void impedirPagadorIgualRecebedor() {
        if (this.getPagador().getUsuario().isIgual(this.getRecebedor().getUsuario())) {
            throw new TransacaoException("O pagador deve ser diferente do recebedor.");
        }
    }
}