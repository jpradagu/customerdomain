package com.nttdata.bootcamp.customerdomain.util.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class ResumenException {

    public static Mono<ResponseEntity<Map<String, Object>>> errorResumenException(Throwable t) {
        Map<String, Object> result = new HashMap<>();
        if ((t instanceof WebExchangeBindException)) {
            return Mono.just(t)
                    .cast(WebExchangeBindException.class)
                    .flatMap(e -> Mono.just(e.getFieldErrors()))
                    .flatMapMany(Flux::fromIterable)
                    .map(fieldError -> "The field " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collectList().flatMap(list -> {
                        result.put("errors", list);
                        return Mono.just(ResponseEntity.badRequest().body(result));
                    });
        } else {
            return Mono.just(t).cast(RuntimeException.class).flatMap(e -> {
                result.put("errors", t.getMessage());
                return Mono.just(ResponseEntity.internalServerError().body(result));

            });
        }
    }
}
