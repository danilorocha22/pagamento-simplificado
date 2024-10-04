package com.danilo.pagamentosimplificado.app.api.controllers;

import com.danilo.pagamentosimplificado.app.api.assemblers.CarteiraAssembler;
import com.danilo.pagamentosimplificado.app.api.assemblers.TransferenciaAssembler;
import com.danilo.pagamentosimplificado.app.api.dto.input.TransferenciaInput;
import com.danilo.pagamentosimplificado.app.api.dto.output.CarteiraOutput;
import com.danilo.pagamentosimplificado.app.api.dto.output.TransferenciaOutput;
import com.danilo.pagamentosimplificado.app.domain.entities.Carteira;
import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import com.danilo.pagamentosimplificado.app.domain.services.TransacoesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacoes")
public class TransacoesController {
    private final TransacoesService transacoesService;
    private final CarteiraAssembler carteiraAssembler;
    private final TransferenciaAssembler transferenciaAssembler;

    @PutMapping("/{id}/deposito")
    public ResponseEntity<CarteiraOutput> realizarDeposito(
            @PathVariable("id") Long usuarioId,
            @RequestBody @NotNull BigDecimal valor
    ) {
        Carteira carteira = this.transacoesService.depositar(usuarioId, valor);
        return ResponseEntity.ok(this.carteiraAssembler.toModel(carteira));
    }

    @PostMapping("/transferir")
    public ResponseEntity<TransferenciaOutput> realizarTransferencia(
            @RequestBody @Valid TransferenciaInput transferenciaInput
    ) {
        Transferencia transferencia = this.transferenciaAssembler.toDomain(transferenciaInput);
        transferencia = this.transacoesService.transferir(transferencia);
        return ResponseEntity.ok(this.transferenciaAssembler.toModel(transferencia));
    }
}