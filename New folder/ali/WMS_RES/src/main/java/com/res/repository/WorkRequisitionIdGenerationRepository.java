package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.res.entity.WorkLegacyIdGeneration;
import com.res.entity.WorkRequisitionIdGeneration;

public interface WorkRequisitionIdGenerationRepository extends JpaRepository<WorkRequisitionIdGeneration, Long>{

//	List<WorkRequisitionIdGeneration> findByDivisionAgency(String divisionAgency);
	
	WorkRequisitionIdGeneration findByDivisionAgency(String divisionAgency);

	
}
