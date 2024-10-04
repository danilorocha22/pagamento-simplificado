package com.danilo.pagamentosimplificado.app.api.assemblers;

import com.danilo.pagamentosimplificado.app.api.dto.input.TransferenciaInput;
import com.danilo.pagamentosimplificado.app.api.dto.output.TransferenciaOutput;
import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferenciaAssembler {
    private final ModelMapper mapper;

    public Transferencia toDomain(TransferenciaInput transferenciaInput) {
        return mapper.map(transferenciaInput, Transferencia.class);
    }

    public TransferenciaOutput toModel(Transferencia transferencia) {
        return mapper.map(transferencia, TransferenciaOutput.class);
    }
}