package com.danilo.pagamentosimplificado.app.api.controllers;

import com.danilo.pagamentosimplificado.app.api.assemblers.LojistaAssembler;
import com.danilo.pagamentosimplificado.app.api.dto.input.LojistaInput;
import com.danilo.pagamentosimplificado.app.api.dto.output.LojistaOutput;
import com.danilo.pagamentosimplificado.app.domain.entities.Lojista;
import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/lojista")
public class LojistaController {
    private final UsuarioService cadastroService;
    private final LojistaAssembler lojistaAssembler;

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LojistaOutput> buscar(@PathVariable Long id) {
        Usuario usuario = this.cadastroService.buscarPorId(id);
        Lojista lojista = (Lojista) usuario;
        return ResponseEntity.ok(this.lojistaAssembler.toModel(lojista));
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LojistaOutput> cadastrar(@RequestBody @Valid LojistaInput lojistaInput) {
        Lojista lojista = this.lojistaAssembler.toDomain(lojistaInput);
        Usuario usuario = this.cadastroService.cadastrar(lojista);
        lojista = (Lojista) usuario;
        return ResponseEntity.ok(this.lojistaAssembler.toModel(lojista));
    }
}