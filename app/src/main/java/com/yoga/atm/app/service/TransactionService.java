package com.yoga.atm.app.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.yoga.atm.app.Exception.TransactionFailureException;
import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.model.Transaction;

public interface TransactionService {

	public Account withdraw(String accountNumber, double amount) throws TransactionFailureException;

	public Account transfer(String accountNumber, double amount, String destinationAccountNumber, String reference) throws TransactionFailureException;

	public Long countByAccountNumber(String accountNumber);

	public List<Transaction> findAllByAccountNumber(String accountNumber, Pageable pageable);
	
	public void deleteAll();
	
	public Transaction save(Transaction transaction);

}
