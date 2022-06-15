package com.nttdata.bootcamp.customerdomain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.customerdomain.model.CommercialCustomer;


public interface ComercialCustomerRepository extends ReactiveMongoRepository<CommercialCustomer, String> {

}
