package com.danilo.pagamentosimplificado.app.api.assemblers;

import com.danilo.pagamentosimplificado.app.api.dto.input.ComumInput;
import com.danilo.pagamentosimplificado.app.api.dto.output.ComumOutput;
import com.danilo.pagamentosimplificado.app.domain.entities.Comum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioComumAssembler {
    private final ModelMapper mapper;

    public Comum toDomain(ComumInput input) {
        return mapper.map(input, Comum.class);
    }

    public ComumOutput toModel(Comum comum) {
        return mapper.map(comum, ComumOutput.class);
    }
}