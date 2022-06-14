package com.nttdata.bootcamp.customerdomain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.customerdomain.model.CustomerPersonal;
import com.nttdata.bootcamp.customerdomain.repository.CustomerPersonalRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerPersonalService {

	@Autowired
	private CustomerPersonalRepository personalRepository;
	
	public Flux<CustomerPersonal> findAll(){
		return personalRepository.findAll();
	}
	
	public Mono<CustomerPersonal> findById(String id) {
		return personalRepository.findById(id);
	}

	public Mono<CustomerPersonal> save(CustomerPersonal customer){
		return personalRepository.save(customer);
	}
	
	public Mono<Void> delete(CustomerPersonal customer){
		return personalRepository.delete(customer);
	}

}
