package com.danilo.pagamentosimplificado.app.api.controllers;

import com.danilo.pagamentosimplificado.app.api.assemblers.CarteiraAssembler;
import com.danilo.pagamentosimplificado.app.api.assemblers.TransferenciaAssembler;
import com.danilo.pagamentosimplificado.app.api.dto.input.TransferenciaInput;
import com.danilo.pagamentosimplificado.app.api.dto.output.CarteiraOutput;
import com.danilo.pagamentosimplificado.app.api.dto.output.TransferenciaOutput;
import com.danilo.pagamentosimplificado.app.domain.entities.Carteira;
import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.services.TransacaoService;
import com.danilo.pagamentosimplificado.app.domain.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transacoes")
public class TransacoesController {
    private final TransacaoService transacaoService;
    private final CarteiraAssembler carteiraAssembler;
    private final TransferenciaAssembler transferenciaAssembler;
    private final UsuarioService usuarioService;

    @PutMapping("/{id}/deposito/{valor}")
    public ResponseEntity<CarteiraOutput> deposito(
            @PathVariable("id") Long usuarioId,
            @PathVariable("valor") BigDecimal valor
    ) {
        Carteira carteira = this.transacaoService.depositar(usuarioId, valor);
        return ResponseEntity.ok(this.carteiraAssembler.toModel(carteira));
    }

    @PostMapping("/transferir")
    public ResponseEntity<TransferenciaOutput> transferencia(
            @RequestBody @Valid TransferenciaInput transferenciaInput
    ) {
        Transferencia transferencia = this.transferenciaAssembler.toDomain(transferenciaInput);
        this.configurar(transferenciaInput, transferencia);
        transferencia = this.transacaoService.transferir(transferencia);
        return ResponseEntity.ok(this.transferenciaAssembler.toModel(transferencia));
    }

    private void configurar(TransferenciaInput transferenciaInput, Transferencia transferencia) {
        Usuario recebedor = this.usuarioService.buscarPorId(transferenciaInput.getRecebedor());
        Usuario pagador = this.usuarioService.buscarPorId(transferenciaInput.getPagador());
        transferencia.setRecebedor(recebedor.getCarteira());
        transferencia.setPagador(pagador.getCarteira());
    }
}