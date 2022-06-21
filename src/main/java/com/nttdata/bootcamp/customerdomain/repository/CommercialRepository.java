package com.nttdata.bootcamp.customerdomain.repository;

import com.nttdata.bootcamp.customerdomain.model.CommercialCustomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/** Commercial Customer Repository. */
public interface CommercialRepository extends ReactiveMongoRepository<CommercialCustomer, String> {
  Mono<CommercialCustomer> findByRuc(String ruc);
}
