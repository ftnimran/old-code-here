package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.FinancialStageType;

public interface FinancialStageTypeRepository  extends JpaRepository<FinancialStageType, Long> {
	
	List<FinancialStageType> findByEnabled(Short isEnabled);
	 
}
