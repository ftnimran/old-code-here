package com.res.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.res.entity.BudgetRequestNoGeneration;

@Transactional(readOnly = true)
public interface BudgetRequestNoGenerationRepository extends JpaRepository<BudgetRequestNoGeneration, Long>{

	@Query("select max(w.id) from BudgetRequestNoGeneration w where w.financialYear = :financialYear and w.office = :office  ")
	Long fetchMaxId(@Param("financialYear") String financialYear, @Param("office") String office);
	
	long deleteById(Long id);
	
	public BudgetRequestNoGeneration findByOfficeAndFinancialYear(String office, String financialYear);
}
