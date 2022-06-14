package com.nttdata.bootcamp.customerdomain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.customerdomain.model.CustomerPersonal;

public interface CustomerPersonalRepository extends ReactiveMongoRepository<CustomerPersonal, String> {

}
