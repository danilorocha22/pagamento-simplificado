package com.danilo.pagamentosimplificado.app.api.assemblers;

import com.danilo.pagamentosimplificado.app.api.dto.input.LojistaInput;
import com.danilo.pagamentosimplificado.app.api.dto.output.ComumOutput;
import com.danilo.pagamentosimplificado.app.api.dto.output.LojistaOutput;
import com.danilo.pagamentosimplificado.app.domain.entities.Comum;
import com.danilo.pagamentosimplificado.app.domain.entities.Lojista;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LojistaAssembler {
    private final ModelMapper mapper;

    public Lojista toDomain(LojistaInput input) {
        return mapper.map(input, Lojista.class);
    }

    public LojistaOutput toModel(Lojista lojista) {
        return mapper.map(lojista, LojistaOutput.class);
    }
}