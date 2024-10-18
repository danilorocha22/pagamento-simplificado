package com.danilo.pagamentosimplificado.app.api.exceptionhanlder;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.OffsetDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
@JsonInclude(NON_NULL)
public class Problem {
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private OffsetDateTime timestamp;

    public static Problem newProblem(String msg, HttpStatusCode status) {
        return Problem.builder()
                .title(msg)
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .build();
    }

    public static ProblemBuilder newProblemBuilder(
            ProblemType problemType,
            HttpStatusCode status,
            String detail
    ) {
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .timestamp(OffsetDateTime.now());
    }

    @Getter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;

        public static List<Object> getProblemObjects(
                BindingResult bindingResult,
                MessageSource messageSource
        ) {
            return bindingResult.getAllErrors().stream()
                    .map(objectError -> {
                        String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                        String name = StringUtils.capitalize(objectError.getObjectName());
                        name = formatEntityNameInput(name);

                        if (objectError instanceof FieldError) {
                            name = ((FieldError) objectError).getField();
                        }

                        return Object.builder()
                                .name(name)
                                .userMessage(message)
                                .build();
                    }).toList();
        }

        public static String formatEntityNameInput(String name) {
            return (name.endsWith("Input")) ? name.replace("Input", "") : name;
        }
    }
}