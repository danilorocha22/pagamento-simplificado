package com.danilo.pagamentosimplificado.app.api.controllers;

import com.danilo.pagamentosimplificado.app.api.assemblers.ComumAssembler;
import com.danilo.pagamentosimplificado.app.api.dto.input.ComumInput;
import com.danilo.pagamentosimplificado.app.api.dto.output.ComumOutput;
import com.danilo.pagamentosimplificado.app.domain.entities.Comum;
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
@RequestMapping(value = "/comuns")
public class ComumController {
    private final UsuarioService usuarioService;
    private final ComumAssembler comumAssembler;

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComumOutput> buscar(@PathVariable Long id) {
        Usuario usuario = this.usuarioService.buscarPorId(id);
        Comum comum = (Comum) usuario;
        return ResponseEntity.ok(this.comumAssembler.toModel(comum));
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComumOutput> cadastro(@RequestBody @Valid ComumInput comumInput) {
        Comum comum = this.comumAssembler.toDomain(comumInput);
        Usuario usuario = this.usuarioService.cadastrar(comum);
        comum = (Comum) usuario;
        return ResponseEntity.ok(this.comumAssembler.toModel(comum));
    }
}