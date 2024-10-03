package com.danilo.pagamentosimplificado.app.infra.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.exceptions.NotificacaoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpMethod.POST;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificacaoExternalService {
    public static final String URL_NOTIFY = "https://util.devi.tools/api/v1/notify";
    private final RestTemplate restTemplate;

    public void notificar(Transferencia transferencia) {
        ResponseEntity<Map> autorizacaoResponse = this.restTemplate.exchange(
                URL_NOTIFY,
                POST,
                null,
                Map.class
        );

        if (autorizacaoResponse.getStatusCode().is2xxSuccessful()) {
            if (Objects.requireNonNull(autorizacaoResponse.getBody()).get("status").equals("fail")) {
                log.warn("Notificação não realizada.");
                throw new NotificacaoException();
            }
            Usuario usuario = transferencia.getRecebedor().getUsuario();
            log.info(("Usuário %s, notificado com sucesso  no e-mail: %s."
                    .formatted(usuario.getNomeCompleto(), usuario.getEmail())));
        }
    }
}