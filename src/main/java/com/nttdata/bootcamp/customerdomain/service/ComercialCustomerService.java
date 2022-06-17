package com.nttdata.bootcamp.customerdomain.service;

import com.nttdata.bootcamp.customerdomain.model.CommercialCustomer;
import com.nttdata.bootcamp.customerdomain.model.PersonalCustomer;
import com.nttdata.bootcamp.customerdomain.repository.ComercialCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ComercialCustomerService {

    private final Logger log = LoggerFactory.getLogger(ComercialCustomerService.class);
    @Autowired
    private ComercialCustomerRepository enterpriseRepository;

    public Flux<CommercialCustomer> findAll() {
        log.info("CustomerEnterpriseService findAll ->");
        return enterpriseRepository.findAll();
    }

    public Mono<CommercialCustomer> findById(String id) {
        log.info("CustomerEnterpriseService findById ->");
        return enterpriseRepository.findById(id);
    }

    public Mono<CommercialCustomer> create(CommercialCustomer customer) {
        log.info("CustomerEnterpriseService create ->");
        return enterpriseRepository.findByRuc(customer.getRuc())
                .flatMap(__ -> Mono.error(new RuntimeException("Commercial customer exist!")))
                .switchIfEmpty(Mono.defer(() -> enterpriseRepository.save(customer)))
                .cast(CommercialCustomer.class);
    }

    public Mono<CommercialCustomer> update(CommercialCustomer commercialCustomer, String id) {
        log.info("CustomerEnterpriseService update ->");
        return enterpriseRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Commercial customer not found")))
                .flatMap(p -> enterpriseRepository
                        .findByRuc(commercialCustomer.getRuc())
                        .switchIfEmpty(Mono.defer(() -> enterpriseRepository
                                .save(parseIdToCommercialCustomer(commercialCustomer, id))))
                        .flatMap(obj -> {
                            if (obj != null) {
                                if (obj.getId().equals(id)) {
                                    return enterpriseRepository.save(parseIdToCommercialCustomer(commercialCustomer, id));
                                } else
                                    return Mono.error(new RuntimeException("Commercial customer exist other side!"));
                            } else {
                                return enterpriseRepository.save(parseIdToCommercialCustomer(commercialCustomer, id));
                            }
                        }));
    }

    private CommercialCustomer parseIdToCommercialCustomer(CommercialCustomer commercialCustomer, String id) {
        commercialCustomer.setId(id);
        return commercialCustomer;
    }

    public Mono<Void> delete(CommercialCustomer customer) {
        log.info("CustomerEnterpriseService delete ->");
        return enterpriseRepository.delete(customer);
    }

}
