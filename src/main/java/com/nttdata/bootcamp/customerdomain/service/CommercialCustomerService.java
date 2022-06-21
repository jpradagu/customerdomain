package com.nttdata.bootcamp.customerdomain.service;

import com.nttdata.bootcamp.customerdomain.model.CommercialCustomer;
import com.nttdata.bootcamp.customerdomain.repository.CommercialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Commercial Customer Service.*/
@Service
@Slf4j()
public class CommercialCustomerService {

  @Autowired
  private CommercialRepository enterpriseRepository;

  public Flux<CommercialCustomer> findAll() {
    log.info("CustomerEnterpriseService findAll ->");
    return enterpriseRepository.findAll();
  }

  public Mono<CommercialCustomer> findById(String id) {
    log.info("CustomerEnterpriseService findById ->");
    return enterpriseRepository.findById(id);
  }

  /** create Commercial Customer. */
  public Mono<CommercialCustomer> create(CommercialCustomer customer) {
    log.info("CustomerEnterpriseService create ->");
    return enterpriseRepository.findByRuc(customer.getRuc())
        .flatMap(c -> Mono.error(new RuntimeException("Commercial customer exist!")))
        .switchIfEmpty(Mono.defer(() -> enterpriseRepository
            .save(customer))).cast(CommercialCustomer.class);
  }

  /** Update commercial customer.*/
  public Mono<CommercialCustomer> update(CommercialCustomer commercialCustomer, String id) {
    log.info("CustomerEnterpriseService update ->");
    return enterpriseRepository.findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("Commercial customer not found")))
        .flatMap(p -> enterpriseRepository.findByRuc(commercialCustomer.getRuc())
            .switchIfEmpty(
                Mono.defer(() -> enterpriseRepository
                    .save(parseIdToCommercialCustomer(commercialCustomer, id))))
            .flatMap(obj -> {
              if (obj != null) {
                if (obj.getId().equals(id)) {
                  return enterpriseRepository
                      .save(parseIdToCommercialCustomer(commercialCustomer, id));
                } else {
                  return Mono.error(new RuntimeException("Commercial customer exist other side!"));
                }
              } else {
                return enterpriseRepository
                    .save(parseIdToCommercialCustomer(commercialCustomer, id));
              }
            }));
  }

  /** parse id to commercial customer.*/
  private CommercialCustomer parseIdToCommercialCustomer(CommercialCustomer commercial, String id) {
    commercial.setId(id);
    return commercial;
  }

  public Mono<Void> delete(CommercialCustomer customer) {
    log.info("CustomerEnterpriseService delete ->");
    return enterpriseRepository.delete(customer);
  }

}
