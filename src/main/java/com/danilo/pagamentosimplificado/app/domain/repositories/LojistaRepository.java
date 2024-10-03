package com.danilo.pagamentosimplificado.app.domain.repositories;

import com.danilo.pagamentosimplificado.app.domain.entities.Lojista;
import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LojistaRepository extends JpaRepository<Lojista, Long> {

    Optional<Usuario> findByCnpj(String cnpj);
}