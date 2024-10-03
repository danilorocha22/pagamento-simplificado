package com.danilo.pagamentosimplificado.app.domain.repositories;

import com.danilo.pagamentosimplificado.app.domain.entities.Comum;
import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComumRepository extends JpaRepository<Comum, Long> {

    Optional<Usuario> findByCpf(String cpf);
}