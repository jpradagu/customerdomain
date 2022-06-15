package com.nttdata.bootcamp.customerdomain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.customerdomain.model.PersonalCustomer;

public interface PersonalCustomerRepository extends ReactiveMongoRepository<PersonalCustomer, String> {

}
