package com.nttdata.bootcamp.customerdomain.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.customerdomain.model.CustomerEnterprise;


public interface CustomerEnterpriseRepository extends ReactiveMongoRepository<CustomerEnterprise, String> {

}
