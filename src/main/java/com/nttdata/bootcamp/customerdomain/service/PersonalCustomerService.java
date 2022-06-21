package com.nttdata.bootcamp.customerdomain.service;

import com.nttdata.bootcamp.customerdomain.model.PersonalCustomer;
import com.nttdata.bootcamp.customerdomain.repository.PersonalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Personal customer service.*/
@Service
@Slf4j()
public class PersonalCustomerService {

  @Autowired
  private PersonalRepository personalRepository;

  public Flux<PersonalCustomer> findAll() {
    log.info("CustomerPersonalService findAll ->");
    return personalRepository.findAll();
  }

  public Mono<PersonalCustomer> findById(String id) {
    log.info("CustomerPersonalService findById ->");
    return personalRepository.findById(id);
  }

  /** create personal customer.*/
  public Mono<PersonalCustomer> create(PersonalCustomer customer) {
    log.info("CustomerPersonalService create ->");
    return personalRepository.findByDni(customer.getDni())
            .flatMap(c -> Mono.error(new RuntimeException("Personal customer exist!")))
            .switchIfEmpty(Mono.defer(() -> personalRepository
                    .save(customer)))
            .cast(PersonalCustomer.class);
  }
  
  /** update personal customer.*/
  public Mono<PersonalCustomer> update(PersonalCustomer customer, String id) {
    log.info("CustomerPersonalService update ->");
    return personalRepository.findById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("Personal customer not found")))
            .flatMap(p -> personalRepository.findByDni(customer.getDni())
                    .switchIfEmpty(Mono.defer(() -> personalRepository
                        .save(parseIdToPersonalCustomer(customer, id))))
                    .flatMap(obj -> {
                      if (obj != null) {
                        if (obj.getId().equals(id)) {
                          return personalRepository.save(parseIdToPersonalCustomer(customer, id));
                        } else {
                          return Mono
                              .error(new RuntimeException("Personal customer exist other side!"));
                        }
                      } else {
                        return personalRepository.save(parseIdToPersonalCustomer(customer, id));
                      }
                    }));

  }

  private PersonalCustomer parseIdToPersonalCustomer(PersonalCustomer personalCustomer, String id) {
    personalCustomer.setId(id);
    return personalCustomer;
  }

  public Mono<Void> delete(PersonalCustomer customer) {
    log.info("CustomerPersonalService delete ->");
    return personalRepository.delete(customer);
  }

}
