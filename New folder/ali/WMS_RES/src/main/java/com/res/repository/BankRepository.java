package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.Bank;

public interface BankRepository  extends JpaRepository<Bank, Long> {
	
	List<Bank> findByEnabled(Short isEnabled);

	Page<Bank> findByEnabled(Pageable pageable, Short enabled);

	long countByEnabled(Short enabled);

	Bank findByBankNameAndEnabled(String bankName, Short s);
	
}
