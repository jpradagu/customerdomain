package com.nttdata.bootcamp.customerdomain.controller;

import com.nttdata.bootcamp.customerdomain.model.CommercialCustomer;
import com.nttdata.bootcamp.customerdomain.service.CommercialCustomerService;
import com.nttdata.bootcamp.customerdomain.util.exception.ResumenError;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Commercial Customer Controller.*/
@RestController
@RequestMapping("/api/customer/enterprise")
public class CommercialCustomerController {

  private final Logger log = LoggerFactory.getLogger(CommercialCustomerController.class);
  @Autowired
  private CommercialCustomerService commercialService;

  /** FindAll Commercial Customer.*/
  @GetMapping
  public Mono<ResponseEntity<Flux<CommercialCustomer>>> findAll() {
    log.info("CommercialCustomerController findAll ->");
    return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON).body(commercialService.findAll()));
  }

  /** Find Commercial Customer.*/
  @GetMapping("/{id}")
  public Mono<ResponseEntity<CommercialCustomer>> findById(@PathVariable String id) {
    log.info("CommercialCustomerController findById ->");
    return commercialService.findById(id)
            .map(ce -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ce))
            .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /** Create Commercial Customer.*/
  @PostMapping
  public Mono<ResponseEntity<Map<String, Object>>> create(
          @Valid @RequestBody Mono<CommercialCustomer> monoCommercial) {
    log.info("CommercialCustomerController create ->");
    Map<String, Object> result = new HashMap<>();
    return monoCommercial.flatMap(c -> {
      c.setId(null);
      return commercialService.create(c)
              .map(p -> ResponseEntity
                      .created(URI.create("/api/customer/enterprise/".concat(p.getId())))
                      .contentType(MediaType.APPLICATION_JSON).body(result));
    }).onErrorResume(ResumenError::errorResumenException);
  }

  /** Update Commercial Customer.*/
  @PutMapping("/{id}")
  public Mono<ResponseEntity<Map<String, Object>>> update(
          @Valid @RequestBody Mono<CommercialCustomer> monoCommercial,
          @PathVariable String id) {
    log.info("CommercialCustomerController update ->");
    Map<String, Object> result = new HashMap<>();
    return monoCommercial.flatMap(c -> commercialService.update(c, id).flatMap(enterprise -> {
      result.put("data", enterprise);
      return Mono.just(ResponseEntity.ok().body(result));
    })).onErrorResume(ResumenError::errorResumenException);
  }

  /** Delete Commercial Customer.*/
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
    log.info("CommercialCustomerController delete ->");
    return commercialService.findById(id)
            .flatMap(e -> commercialService
                    .delete(e).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
