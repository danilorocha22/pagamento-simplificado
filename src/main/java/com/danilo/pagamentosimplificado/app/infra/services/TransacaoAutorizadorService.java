package com.danilo.pagamentosimplificado.app.infra.services;

import com.danilo.pagamentosimplificado.app.domain.exceptions.TransacaoNaoAutorizadaException;
import com.danilo.pagamentosimplificado.app.domain.exceptions.TransferenciaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoAutorizadorService {
    public static final String URL_AUTHORIZE = "https://util.devi.tools/api/v2/authorize";
    private final RestTemplate restTemplate;

    public void autorizarTransacao() {
        ResponseEntity<Map> autorizacaoResponse = this.restTemplate.getForEntity(URL_AUTHORIZE, Map.class);
        if (autorizacaoResponse.getStatusCode().is2xxSuccessful()) {
            if (Objects.requireNonNull(autorizacaoResponse.getBody()).get("authorization").equals(false)) {
                log.warn("Transação não autorizada");
                throw new TransacaoNaoAutorizadaException();
            }
            log.info("Transação autorizada");
        }
    }

}
