package com.nttdata.bootcamp.customerdomain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.customerdomain.model.PersonalCustomer;
import com.nttdata.bootcamp.customerdomain.repository.PersonalCustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerPersonalService {

	@Autowired
	private PersonalCustomerRepository personalRepository;
	
	public Flux<PersonalCustomer> findAll(){
		return personalRepository.findAll();
	}
	
	public Mono<PersonalCustomer> findById(String id) {
		return personalRepository.findById(id);
	}

	public Mono<PersonalCustomer> save(PersonalCustomer customer){
		return personalRepository.save(customer);
	}
	
	public Mono<Void> delete(PersonalCustomer customer){
		return personalRepository.delete(customer);
	}

}
