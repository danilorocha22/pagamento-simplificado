package com.danilo.pagamentosimplificado.app.api.assemblers;

import com.danilo.pagamentosimplificado.app.api.dto.output.CarteiraOutput;
import com.danilo.pagamentosimplificado.app.domain.entities.Carteira;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarteiraAssembler {
    private final ModelMapper mapper;

    public CarteiraOutput toModel(Carteira carteira) {
        return mapper.map(carteira, CarteiraOutput.class);
    }
}