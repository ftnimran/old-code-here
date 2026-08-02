package com.res.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.res.entity.BudgetRequest;

public interface BudgetRequestRepository extends JpaRepository <BudgetRequest, Long>, CrudRepository<BudgetRequest, Long> {

	@Query("from BudgetRequest b where b.status.statusId > :status")
	Page<BudgetRequest> findByStatus(Pageable pageable, @Param("status") Long status);
	
}
