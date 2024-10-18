package com.danilo.pagamentosimplificado.app.domain.services.strategy.config;

import com.danilo.pagamentosimplificado.app.domain.services.strategy.UsuarioValidacaoStrategy;
import com.danilo.pagamentosimplificado.app.domain.services.strategy.impl.CnpjValidacaoStrategy;
import com.danilo.pagamentosimplificado.app.domain.services.strategy.impl.CpfValidacaoStrategy;
import com.danilo.pagamentosimplificado.app.domain.services.strategy.impl.EmailValidacaoStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ValidacaoUsuarioConfig {

    @Bean
    public List<UsuarioValidacaoStrategy> estrategias(
            EmailValidacaoStrategy emailValidacaoStrategy,
            CpfValidacaoStrategy cpfValidacaoStrategy,
            CnpjValidacaoStrategy cnpjValidacaoStrategy
    ) {
        return List.of(emailValidacaoStrategy, cpfValidacaoStrategy, cnpjValidacaoStrategy);
    }
}
