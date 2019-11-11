package com.yoga.atm.app.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.yoga.atm.app.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	public Long countByAccountAccountNumber(String accountNumber);

	public List<Transaction> findAllByAccountAccountNumber(String accountNumber, Pageable pageable);

}
