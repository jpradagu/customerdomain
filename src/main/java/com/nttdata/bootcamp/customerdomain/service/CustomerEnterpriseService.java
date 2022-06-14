package com.nttdata.bootcamp.customerdomain.service;

import java.util.ArrayList;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.customerdomain.model.BankAccount;
import com.nttdata.bootcamp.customerdomain.model.CustomerEnterprise;
import com.nttdata.bootcamp.customerdomain.repository.CustomerEnterpriseRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerEnterpriseService {

    private Logger log = LoggerFactory.getLogger(CustomerEnterpriseService.class);
    @Autowired
    private CustomerEnterpriseRepository enterpriseRepository;

    @Autowired
    private WebClient.Builder webClient;

    public Flux<CustomerEnterprise> findAll() {
        log.debug("CustomerEnterpriseService findAll -> ");
        return enterpriseRepository.findAll();
    }

    public Mono<CustomerEnterprise> findById(String id) {
        return enterpriseRepository.findById(id);
    }

    public Mono<CustomerEnterprise> save(CustomerEnterprise customer) {

        return enterpriseRepository.save(customer);
    }

    public Mono<Void> delete(CustomerEnterprise customer) {
        return enterpriseRepository.delete(customer);
    }

    public Mono<CustomerEnterprise> findAllBankAccountByCustomerId(String id) {
        log.info("CustomerEnterpriseService findAllBankAccountByCustomerId -> {}", id);
        return enterpriseRepository.findById(id).flatMap(ent -> {
            ent.setBankAccounts(new ArrayList<BankAccount>());
            return webClient.build()
                    .get()
                    .uri("http://productdomain/api/product/bank-account/customer/{id}",
                            Collections.singletonMap("id", ent.getId()))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToFlux(BankAccount.class)
                    .collectList()
                    .flatMap(p -> {
                        ent.setBankAccounts(p);
                        return Mono.just(ent);
                    });
        });
    }

}
