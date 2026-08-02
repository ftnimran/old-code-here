package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.BudgetRequest;
import com.res.entity.BudgetRequestDetail;

public interface BudgetRequestDetailRepository extends JpaRepository <BudgetRequestDetail, Long>, CrudRepository<BudgetRequestDetail, Long> {

	
	Page<BudgetRequestDetail> findByBudgetRequest(Pageable pageable, BudgetRequest budgetRequest);
	
	List<BudgetRequestDetail> findByBudgetRequest(BudgetRequest budgetRequest);
	
}
