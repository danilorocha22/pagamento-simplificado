package com.danilo.pagamentosimplificado.app.api.controllers;

import com.danilo.pagamentosimplificado.app.api.exceptionhanlder.Problem;
import com.danilo.pagamentosimplificado.app.api.exceptionhanlder.ProblemType;
import com.danilo.pagamentosimplificado.app.domain.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.danilo.pagamentosimplificado.app.api.exceptionhanlder.Problem.newProblemBuilder;
import static com.danilo.pagamentosimplificado.app.api.exceptionhanlder.ProblemType.*;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    public static final String MSG_SE_PROBLEMA_PERSISTIR = "Se o problema persistir, entre em contato com o " +
            "suporte técnico.";
    public static final String MSG_ERRO_GENERICO_SERVIDOR = "Ops! Parece que algo deu errado no servidor. " +
            MSG_SE_PROBLEMA_PERSISTIR;
    public static final String MSG_ERRO_GENERICO_CLIENTE = "Ops! Parece que algo deu errado do seu lado. Por favor, " +
            "verifique os dados que você forneceu e tente novamente. " + MSG_SE_PROBLEMA_PERSISTIR;


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handlerEntityNotFoundException(@NonNull EntityNotFoundException ex, WebRequest req) {
        HttpStatusCode statusCode = HttpStatus.NOT_FOUND;
        Problem problem = newProblemBuilder(RECURSO_NAO_ENCONTRADO, statusCode, ex.getMessage())
                .userMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusCode, req);
    }

    @ExceptionHandler(UsuarioExisteException.class)
    public ResponseEntity<?> handlerUsuarioExisteException(@NonNull UsuarioExisteException ex, WebRequest req) {
        HttpStatusCode statusCode = HttpStatus.BAD_REQUEST;
        Problem problem = newProblemBuilder(RECURSO_EXISTENTE, statusCode, ex.getMessage())
                .userMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusCode, req);
    }

    @ExceptionHandler(TransacaoNaoAutorizadaException.class)
    public ResponseEntity<?> handlerTransacaoNaoAutorizadaException(
            @NonNull TransacaoNaoAutorizadaException ex,
            WebRequest req
    ) {
        HttpStatusCode statusCode = HttpStatus.UNAUTHORIZED;
        Problem problem = newProblemBuilder(NAO_AUTORIZADO, statusCode, ex.getMessage())
                .userMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusCode, req);
    }

    @ExceptionHandler(TransacaoException.class)
    public ResponseEntity<?> handlerTransacaoException(@NonNull TransacaoException ex, WebRequest req) {
        HttpStatusCode statusCode = HttpStatus.BAD_REQUEST;
        Problem problem = newProblemBuilder(ERRO_NA_REQUISICAO, statusCode, ex.getMessage())
                .userMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusCode, req);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handlerNegocioException(@NonNull NegocioException ex, WebRequest req) {
        HttpStatusCode statusCode = HttpStatus.BAD_REQUEST;
        Problem problem = newProblemBuilder(ERRO_NA_REQUISICAO, statusCode, ex.getMessage())
                .userMessage(MSG_ERRO_GENERICO_CLIENTE)
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusCode, req);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(@NonNull Exception ex, WebRequest req) {
        HttpStatusCode statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        Problem problem = newProblemBuilder(ERRO_INTERNO_DO_SISTEMA, statusCode, ex.getMessage())
                .userMessage(MSG_ERRO_GENERICO_SERVIDOR)
                .build();

        log.error("handlerException() -> Erro {}", ex.getLocalizedMessage(), ex);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusCode, req);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex,
            Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request
    ) {
        if (body == null) {
            body = Problem.newProblem(ex.getMessage(), statusCode);
        } else if (body instanceof String) {
            body = Problem.newProblem((String) body, statusCode);
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
