package com.nttdata.bootcamp.customerdomain.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "customerEnteprises")
@Data
public class CustomerEnterprise {
	@Id
	private String id;
	@NotEmpty
	private String ruc;
	@NotEmpty
	private String reasonSocial;
	@NotEmpty
	private String owner;
	private String address;
	private List<BankAccount> bankAccounts;
}
