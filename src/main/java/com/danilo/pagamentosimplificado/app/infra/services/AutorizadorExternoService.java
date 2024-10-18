package com.danilo.pagamentosimplificado.app.infra.services;

import com.danilo.pagamentosimplificado.app.domain.exceptions.TransacaoNaoAutorizadaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.lang.Boolean.FALSE;

@Slf4j
@Service
@RequiredArgsConstructor
public class AutorizadorExternoService {
    public static final String URL_AUTHORIZE = "https://util.devi.tools/api/v2/authorize";
    private final RestTemplate restTemplate;

    public void autorizarTransacao() {
        ResponseEntity<Map> autorizacaoResponse = this.restTemplate.getForEntity(URL_AUTHORIZE, Map.class);

        if (autorizacaoResponse.getStatusCode().is2xxSuccessful()) {
            autorizacaoResponse.getBody().forEach((key, value) -> {
                if ("authorization".equals(key)) {
                    if (FALSE.equals(value)) {
                        log.warn("Transação não autorizada.");
                        throw new TransacaoNaoAutorizadaException();
                    }
                    log.info("Transação autorizada");
                }
            });
        } else {
            log.warn("Transação não autorizada. Houve uma falha ao tentar autorizar a transação.");
            throw new TransacaoNaoAutorizadaException();
        }
    }
}