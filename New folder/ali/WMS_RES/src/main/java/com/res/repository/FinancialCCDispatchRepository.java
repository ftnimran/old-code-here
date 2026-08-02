package com.res.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.FinancialCCDispatchDetails;
import com.res.entity.PhysicalCCDispatchDetails;
import com.res.entity.Work;

public interface FinancialCCDispatchRepository extends JpaRepository <FinancialCCDispatchDetails, Long>, CrudRepository<FinancialCCDispatchDetails, Long> {
	
	public FinancialCCDispatchDetails findByWork(Work work);
	
}
