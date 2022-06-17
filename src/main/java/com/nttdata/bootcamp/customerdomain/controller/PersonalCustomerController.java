package com.nttdata.bootcamp.customerdomain.controller;

import com.nttdata.bootcamp.customerdomain.model.PersonalCustomer;
import com.nttdata.bootcamp.customerdomain.service.PersonalCustomerService;
import com.nttdata.bootcamp.customerdomain.util.exception.ResumenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customer/personal")
public class PersonalCustomerController {

    private final Logger log = LoggerFactory.getLogger(PersonalCustomerController.class);

    @Autowired
    private PersonalCustomerService personalService;

    @GetMapping
    public Mono<ResponseEntity<Flux<PersonalCustomer>>> findAll() {
        log.info("PersonalCustomerController findAll ->");
        return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(personalService.findAll()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PersonalCustomer>> findById(@PathVariable String id) {
        log.info("PersonalCustomerController findById ->");
        return personalService.findById(id)
                .map(ce -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ce))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> create(@Valid @RequestBody Mono<PersonalCustomer> monoPersonal) {
        log.info("PersonalCustomerController create ->");
        Map<String, Object> result = new HashMap<>();
        return monoPersonal.flatMap(c -> {
            c.setId(null);
            return personalService.create(c).map(p -> ResponseEntity
                    .created(URI.create("/api/customer/personal/".concat(p.getId())))
                    .contentType(MediaType.APPLICATION_JSON).body(result));
        }).onErrorResume(ResumenException::errorResumenException);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> update(@Valid @RequestBody Mono<PersonalCustomer> monoPersonal, @PathVariable String id) {
        log.info("PersonalCustomerController update ->");
        Map<String, Object> result = new HashMap<>();
        return monoPersonal.flatMap(c -> personalService.update(c, id).flatMap(persona -> {
            result.put("data", persona);
            return Mono.just(ResponseEntity.ok().body(result));
        })).onErrorResume(ResumenException::errorResumenException);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        log.info("PersonalCustomerController delete ->");
        return personalService.findById(id)
                .flatMap(p -> personalService.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
