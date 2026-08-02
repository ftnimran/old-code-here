package com.res.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.res.entity.BudgetAllotment;
import com.res.entity.BudgetAllotmentEEOffice;

public interface BudgetAllotmentEEOfficeRepository extends 	JpaRepository<BudgetAllotmentEEOffice, Long>, CrudRepository<BudgetAllotmentEEOffice, Long> {

	Page<BudgetAllotmentEEOffice> findByBudgetAllotment(Pageable pageable, 	BudgetAllotment budgetAllotment);

	List<BudgetAllotmentEEOffice> findByBudgetAllotment(BudgetAllotment budgetAllotment);

	List<BudgetAllotmentEEOffice> findByaccountHeadIdAndOfficeIdAndLapsed(Long AccHeadId, Long officeId, boolean lapsed);

	List<BudgetAllotmentEEOffice> findByOfficeIdAndLapsed(Long officeId, boolean lapsed);

	 /*@Query("SELECT new  com.res.entity.BudgetAllotmentEEOffice(sum(amount) AS amount, b.accountHead AS accountHead,  b.office as office) from BudgetAllotmentEEOffice b where b.office.id = :officeId and b.lapsed = :lapsed  group By  b.accountHead, b.office ")
	List<BudgetAllotmentEEOffice> getBudgetAllotmentEEOffice(@Param ("officeId") Long officeId, @Param ("lapsed") boolean lapsed); */
	
}
