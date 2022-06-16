package com.nttdata.bootcamp.customerdomain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.customerdomain.model.PersonalCustomer;
import reactor.core.publisher.Mono;

public interface PersonalCustomerRepository extends ReactiveMongoRepository<PersonalCustomer, String> {
    Mono<PersonalCustomer> findByDni(String dni);
}
