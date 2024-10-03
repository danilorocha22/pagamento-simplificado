package com.danilo.pagamentosimplificado.app.api.controllers;

import com.danilo.pagamentosimplificado.app.api.assemblers.UsuarioComumAssembler;
import com.danilo.pagamentosimplificado.app.api.dto.input.ComumInput;
import com.danilo.pagamentosimplificado.app.api.dto.output.ComumOutput;
import com.danilo.pagamentosimplificado.app.domain.entities.Comum;
import com.danilo.pagamentosimplificado.app.domain.services.UsuarioCadastroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comum")
public class UsuarioComumController {
    private final UsuarioCadastroService cadastroService;
    private final UsuarioComumAssembler comumAssembler;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComumOutput> cadastro(@RequestBody @Valid ComumInput comumInput) {
        Comum usuario = this.comumAssembler.toDomain(comumInput);
        usuario = (Comum) this.cadastroService.cadastrarUsuario(usuario);
        return ResponseEntity.ok(this.comumAssembler.toModel(usuario));
    }
}