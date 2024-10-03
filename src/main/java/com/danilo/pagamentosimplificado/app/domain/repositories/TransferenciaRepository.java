package com.danilo.pagamentosimplificado.app.domain.repositories;

import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}