package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.FinancialYear;
import com.res.entity.LineDepartment;

public interface FinancialYearRepository  extends JpaRepository<FinancialYear, Long> {
	
	List<FinancialYear> findByEnabled(Short isEnabled);
	
	List<FinancialYear> findByFinancialYearAndEnabled(String fyName, Short isEnabled);
    
}
