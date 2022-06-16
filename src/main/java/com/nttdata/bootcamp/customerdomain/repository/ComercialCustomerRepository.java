package com.nttdata.bootcamp.customerdomain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.customerdomain.model.CommercialCustomer;

import reactor.core.publisher.Mono;


public interface ComercialCustomerRepository extends ReactiveMongoRepository<CommercialCustomer, String> {

	Mono<CommercialCustomer> findByRuc(String ruc);
}
