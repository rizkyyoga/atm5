package com.yoga.atm.app.service;

import com.yoga.atm.app.model.Account;

public interface TransactionService {

	public Account withdraw(String accountNumber, double amount);

	public Account transfer(String accountNumber, double amount, String destinationAccountNumber, String reference);

}
