package com.nttdata.bootcamp.customerdomain.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "customerEnteprises")
@Getter
@Setter
public class CommercialCustomer extends Person{
	@NotEmpty
	private String ruc;
	@NotEmpty
	private String reasonSocial;
	
	private String address;
	@Transient
	private List<BankAccount> bankAccounts;

}
