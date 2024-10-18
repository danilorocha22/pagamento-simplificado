package com.danilo.pagamentosimplificado.app.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = SINGLE_TABLE)
@ToString(of = {"id", "nomeCompleto", "email"})
public abstract class Usuario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5, message = "O nome deve ter no mínimo 5 letras")
    private String nomeCompleto;

    @NotBlank
    @Column(unique = true)
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    @OneToOne(mappedBy = "usuario", cascade = ALL)
    private Carteira carteira;

    public Usuario() {
        this.carteira = new Carteira();
        this.carteira.setUsuario(this);
    }

    public boolean isIgual(Usuario usuario) {
        return this.id != null && this.id.equals(usuario.getId());
    }

    public boolean isDiferente(Usuario usuario) {
        return !this.isIgual(usuario);
    }

    public boolean isLogista() {
        return this instanceof Lojista;
    }

    public boolean isComum() {
        return this instanceof Comum;
    }
}