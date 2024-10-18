package com.danilo.pagamentosimplificado.app.infra.services;

import com.danilo.pagamentosimplificado.app.domain.entities.Transferencia;
import com.danilo.pagamentosimplificado.app.domain.entities.Usuario;
import com.danilo.pagamentosimplificado.app.domain.exceptions.NotificacaoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpMethod.POST;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificacaoExternaService {
    public static final String URL_NOTIFY = "https://util.devi.tools/api/v2/notify";
    private final RestTemplate restTemplate;

    public void notificar(Transferencia transferencia) {
        try {
            ResponseEntity<Map> autorizacaoResponse = this.restTemplate.exchange(
                    URL_NOTIFY,
                    POST,
                    null,
                    Map.class
            );

            if (autorizacaoResponse.getStatusCode().is2xxSuccessful()) {
                if (requireNonNull(autorizacaoResponse.getBody()).get("status").equals("fail")) {
                    log.warn("Notificação não realizada.");
                    throw new NotificacaoException();
                } else {
                    Usuario usuario = transferencia.getRecebedor().getUsuario();
                    log.info(("Usuário %s, notificado com sucesso no e-mail: %s."
                            .formatted(usuario.getNomeCompleto(), usuario.getEmail())));
                }
            }

        } catch (HttpServerErrorException | NullPointerException ex) {
            log.warn("Notificação não realizada. Houve uma falha no serviço de notificação.");
            throw new NotificacaoException();
        }
    }
}