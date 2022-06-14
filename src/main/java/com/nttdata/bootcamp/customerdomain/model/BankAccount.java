package com.nttdata.bootcamp.customerdomain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BankAccount {

	private String id;
	private String typeAccountBank;
	private String numberAccount;
	private BigDecimal amount;
}
