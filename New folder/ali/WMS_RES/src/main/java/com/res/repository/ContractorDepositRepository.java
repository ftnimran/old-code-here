package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.ContractorDeposit;
import com.res.entity.WorkTender;

public interface ContractorDepositRepository extends JpaRepository<ContractorDeposit, Long> {
	
	public List<ContractorDeposit> findByWorkTender(WorkTender workTender);

}
