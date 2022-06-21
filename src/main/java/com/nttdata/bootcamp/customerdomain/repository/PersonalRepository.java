package com.nttdata.bootcamp.customerdomain.repository;

import com.nttdata.bootcamp.customerdomain.model.PersonalCustomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/** Personal Customer Repository. */
public interface PersonalRepository extends ReactiveMongoRepository<PersonalCustomer, String> {
  Mono<PersonalCustomer> findByDni(String dni);
}
