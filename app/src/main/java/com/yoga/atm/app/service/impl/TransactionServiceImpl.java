package com.yoga.atm.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoga.atm.app.dao.AccountRepository;
import com.yoga.atm.app.dao.TransactionRepository;
import com.yoga.atm.app.enumerable.TransactionType;
import com.yoga.atm.app.model.Account;
import com.yoga.atm.app.model.Transaction;
import com.yoga.atm.app.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	@Transactional
	public Account withdraw(String accountNumber, double amount) {
		Account account = null;
		try {
			account = accountRepository.findByAccountNumber(accountNumber).get(0);
			account.setBalance(account.getBalance() - Double.valueOf(amount));
			Transaction transaction = new Transaction(TransactionType.WITHDRAW, account, Double.valueOf(amount),
					new Date(), null, null);
			account = accountRepository.save(account);
			transactionRepository.save(transaction);
		} catch (Exception e) {
			e.printStackTrace();
			account = null;
			throw new RuntimeException(e.getMessage());
		}
		return account;
	}

	@Override
	@Transactional
	public Account transfer(String accountNumber, double amount, String destinationAccountNumber, String reference) {
		Account account = null;
		try {
			account = accountRepository.findByAccountNumber(accountNumber).get(0);
			Account destAccount = accountRepository.findByAccountNumber(destinationAccountNumber).get(0);
			account.setBalance(account.getBalance() - Double.valueOf(amount));
			destAccount.setBalance(destAccount.getBalance() + Double.valueOf(amount));
			Transaction transaction = new Transaction(TransactionType.TRANSFER, account, Double.valueOf(amount),
					new Date(), destAccount, reference);
			account = accountRepository.save(account);
			accountRepository.save(destAccount);
			transactionRepository.save(transaction);
		} catch (Exception e) {
			e.printStackTrace();
			account = null;
			throw new RuntimeException(e.getMessage());
		}
		return account;
	}

}
