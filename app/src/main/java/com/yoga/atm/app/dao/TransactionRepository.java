package com.yoga.atm.app.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.yoga.atm.app.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	Long countByAccountAccountNumber(String accountNumber);

	List<Transaction> findAllByAccountAccountNumber(String accountNumber, Pageable pageable);

	@Query(value = "select * from transaction where account = ?1 order by date desc limit ?2", nativeQuery = true)
	List<Transaction> findNTransaction(String accountNumber, Integer n);

	@Query(value = "select * from transaction where account = ?1 order by date desc limit ?2,?3", nativeQuery = true)
	List<Transaction> findPagedTransaction(String accountNumber, Integer start, Integer n);

}
