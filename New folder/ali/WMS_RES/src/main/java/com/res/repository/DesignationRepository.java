package com.res.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.res.entity.Designation;

public interface DesignationRepository  extends JpaRepository<Designation, Long> {
	
	List<Designation> findByEnabledOrderById(Short isEnabled);
	
	@Query("from Designation d where d.id in :tsList")
	List<Designation> findByTechnicalSanctionList(@Param("tsList") List<Long> tsList);
    
	@Query("from Designation d where d.id in (1,2,3) and d.enabled=1")
	List<Designation> findOfficerTypeForInspection();

	
    
}
