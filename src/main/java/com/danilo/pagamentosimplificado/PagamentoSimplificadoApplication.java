package com.danilo.pagamentosimplificado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PagamentoSimplificadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagamentoSimplificadoApplication.class, args);
    }
}